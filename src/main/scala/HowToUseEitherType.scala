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


  // sample
  def f(messageId: MessageId): Either[String, Message] = {
    val targetMessage = MessageService.find(MessageFindForm(messageId = messageId))
    targetMessage.headOption.toRight(s"$messageId not found.")
  }

  val result1: Seq[Either[String, Message]] = messageIds.map(f)
  val errors1: Seq[Either[String, Message]] = result1.filter(_.isLeft)
  val errors2: Seq[String] = result1.collect({case Left(errorMessage) => errorMessage})

  // List[List[Message]]みたいにネストしている構造を文字通りフラットにしてくれる
  val result2: List[Message] = messageIds.flatMap(messageId => MessageService.find(MessageFindForm(messageId = messageId)))
  val result3: List[Message] = messageIds.map(messageId => MessageService.find(MessageFindForm(messageId = messageId))).flatten
}
