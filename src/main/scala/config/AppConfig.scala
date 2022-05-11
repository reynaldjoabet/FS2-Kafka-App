package config
import cats.implicits._
import ciris.ConfigValue
final case class AppConfig(serverConfig:ServerConfig,kafkaConfig: KafkaConfig)


object AppConfig{

    import cats.effect.Async

    def config[F[_]:Async]: ConfigValue[F,AppConfig]=
        (ServerConfig.serverConfig[F],KafkaConfig.kafkaConfig[F])
          .parMapN((serverConfig,kafkaConfig)=>AppConfig(serverConfig,kafkaConfig))


}
