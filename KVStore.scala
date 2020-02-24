import java.nio.ByteBuffer

import ckite.{CKite, CKiteBuilder}
import ckite.rpc.{FinagleThriftRpc, ReadCommand, WriteCommand}
import ckite.statemachine.StateMachine
import ckite.util.Serializer

import scala.collection.mutable

//KVStore is an in-memory distributed Map allowing Puts and Gets operations
class KVStore extends StateMachine {

  private var map = mutable.Map[String, String]()
  private var lastIndex: Long = 0

  //Called when a consensus has been reached for a WriteCommand
  //index associated to the write is provided to implement your own persistent semantics
  //see lastAppliedIndex
  def applyWrite: PartialFunction[(Long, WriteCommand[_]), String] = {
    case (index, Put(key: String, value: String)) =>
      map.put(key, value)
      lastIndex = index
      value
  }

  //called when a read command has been received
  def applyRead: PartialFunction[ReadCommand[_], Option[String]] = {
    case Get(key) => map.get(key)
  }

  //CKite needs to know the last applied write on log replay to
  //provide exactly-once semantics
  //If no persistence is needed then state machines can just return zero
  def getLastAppliedIndex: Long = lastIndex

  //called during Log replay on startup and upon installSnapshot requests
  def restoreSnapshot(byteBuffer: ByteBuffer): Unit = {
    map = Serializer.deserialize[mutable.Map[String, String]](byteBuffer.array())
  }
  //called when Log compaction is required
  def takeSnapshot(): ByteBuffer = ByteBuffer.wrap(Serializer.serialize(map))

}

//WriteCommands are replicated under Raft rules
case class Put(key: String, value: String) extends WriteCommand[String]

//ReadCommands are not replicated but forwarded to the Leader
case class Get(key: String) extends ReadCommand[Option[String]]


object hi{
  val ckite: CKite = CKiteBuilder().listenAddress("node1:9091").rpc(FinagleThriftRpc) //Finagle based transport
    .stateMachine(new KVStore()) //KVStore is an implementation of the StateMachine trait
    .bootstrap(true) //bootstraps a new cluster. only needed just the first time for the very first node
    .build

  def main(args: Array[String]): Unit = {
    ckite.start()

  }
}