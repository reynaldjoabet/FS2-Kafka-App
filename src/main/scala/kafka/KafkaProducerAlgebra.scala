package kafka
import fs2.kafka.Serializer
import model._
import io.circe.syntax._
import  fs2.kafka.KafkaProducer
import fs2.kafka.{Acks, ProducerSettings}
import cats.implicits._
import  cats.effect.std.Console
import cats.effect.Async
import config.KafkaConfig
trait KafkaProducerAlgebra[F[_]] {
 def publish(event:Event):F[Unit]
}

object KafkaProducerAlgebra{

  def impl[F[_]:Async:Console](kafkaConfig: KafkaConfig): KafkaProducerAlgebra[F]= new KafkaProducerAlgebra[F] {
    override def publish(event: Event): F[Unit] = {


      val valueSerializer: Serializer[F,Event]= Serializer[F,String]
        .contramap[Event](_.asJson.toString())

      val producerSettings=ProducerSettings(
        keySerializer = Serializer[F,Unit],
        valueSerializer=valueSerializer
      ).withBootstrapServers(kafkaConfig.bootstrapServer.value)
        .withAcks(Acks.One)

      KafkaProducer.stream(producerSettings)
        .evalTapChunk{ producer=>

          import fs2.kafka.{ProducerRecord, ProducerRecords}
          val record: ProducerRecord[Unit, Event] = ProducerRecord(kafkaConfig.topic.value,(),event)
          producer.produce(ProducerRecords.one(record)).flatten

        }.evalTapChunk( _ =>Console[F].println(s"The event has been published") )
        .compile.drain
    }
  }
}