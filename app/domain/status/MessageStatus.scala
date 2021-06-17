package domain.status

sealed trait MessageStatus

object MessageStatus {
  case object FAIL                 extends MessageStatus
  case object WAITING_CONFIRMATION extends MessageStatus
  case object SUCCESS              extends MessageStatus
}
