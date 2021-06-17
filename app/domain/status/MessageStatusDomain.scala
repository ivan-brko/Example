package domain.status

package domain.status

import zio.Task

class MessageStatusDomain {

  def storeMessageStatus(messageId: String, messageStatus: MessageStatus): Task[MessageStatus] =
    Task.succeed(messageStatus)
  def getMessageStatus(messageId: String): Task[MessageStatus]                                 = Task.succeed(MessageStatus.SUCCESS)
}
