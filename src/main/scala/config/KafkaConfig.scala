package config
import ciris.{env,ConfigValue}
import cats.implicits._
final case class KafkaConfig(bootstrapServer:BootstrapServer,groupId:GroupId,topic:Topic)


object  KafkaConfig{

  import cats.effect.Async
  import ciris.Effect

  def kafkaConfig[F[_]:Async]: ConfigValue[F, KafkaConfig] =(
    env("BOOTSTRAP_SERVER").as[String].default("localhost:9092"),
    env("GROUP_ID").as[String].default("consumerGroup"),
    env("KAFKA_TOPIC").as[String].default("eventsTopic")
    ).parMapN((server,groupId,topic)=>
  KafkaConfig(BootstrapServer(server),GroupId(groupId),Topic(topic))
  )
}