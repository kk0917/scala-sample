import scala.collection.mutable.ListBuffer

class Message
trait MyApp extends App {
  type MessageId
  case class MessageFindForm(messageId: MessageId)
  object MessageService {
    def find(messageFindForm: MessageFindForm): List[Message] = ???
  }
  def messageIds: List[MessageId]

  // Before
  val messageList = new ListBuffer[Message]
  messageIds.foreach(messageId => {
    val targetMessage = MessageService.find(MessageFindForm(messageId = messageId))
    if (targetMessage.isEmpty) {
      // エラー処理
    } else {
      messageList += targetMessage.head
    }
  })

  // After
  val result: Seq[Either[String, Message]] = messageIds.map(messageId => {
    val targetMessage = MessageService.find(MessageFindForm(messageId = messageId))
    targetMessage.headOption.toRight(s"$messageId not found.")
  })
  val messageList2: Seq[Message] = result.collect { case Right(message) => message }
}
