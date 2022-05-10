package config

import cats.effect.IO
import ciris.ConfigValue
final case class AppConfig(serverConfig:ServerConfig)


object AppConfig{
    def config: ConfigValue[IO,AppConfig]=(
ServerConfig.serverConfig.map(serverConfig=>AppConfig(serverConfig))
    )

}
