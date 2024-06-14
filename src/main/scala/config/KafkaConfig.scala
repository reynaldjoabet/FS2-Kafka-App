package config

import cats.implicits._

import ciris.{env, ConfigValue}

final case class KafkaConfig(
  bootstrapServer: BootstrapServer,
  groupId: GroupId,
  topic: Topic
)

object KafkaConfig {

  import cats.effect.Async

  import ciris.Effect

  def kafkaConfig[F[_]: Async]: ConfigValue[F, KafkaConfig] = (
    env("BOOTSTRAP_SERVER").as[String].default("192.168.8.133:9092"),
    env("GROUP_ID").as[String].default("consumerGroup"),
    env("KAFKA_TOPIC").as[String].default("topic-C")
  ).parMapN((server, groupId, topic) =>
    KafkaConfig(BootstrapServer(server), GroupId(groupId), Topic(topic))
  )

}
