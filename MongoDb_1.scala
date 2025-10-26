import com.mongodb.{ServerApi, ServerApiVersion}
import org.mongodb.scala._
import org.mongodb.scala.bson.Document
import org.mongodb.scala.model.Filters.equal

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Using

object MongoDb_1 {

  def main(args: Array[String]): Unit = {

    val userName = "rubaawwad"
    val password = "401610357"

    val connectionString =
      s"mongodb+srv://$userName:$password@cluster0.euoitdu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"

    // Construct a ServerApi instance
    val serverApi = ServerApi.builder()
      .version(ServerApiVersion.V1)
      .build()

    val settings = MongoClientSettings.builder()
      .applyConnectionString(new ConnectionString(connectionString))
      .serverApi(serverApi)
      .build()

    // Create a new client and connect to the server
    Using(MongoClient(settings)) { mongoClient =>

      val database = mongoClient.getDatabase("Sample_Resturant")
      val collection = database.getCollection("Resturants")

      // Ping to confirm connection
      val ping = database.runCommand(Document("ping" -> 1)).head()
      Await.result(ping, 10.seconds)
      println("✅ Pinged your deployment. You successfully connected to MongoDB!")

      // Find a restaurant by name
      collection.find(equal("name", "Brunos On The Boulevard"))
        .subscribe(
          (doc: Document) => println(s"📄 Found: ${doc.toJson()}"),
          (e: Throwable) => println(s"❌ Query error: $e"),
          () => println("✔️ Query completed.")
        )

      // Wait for async operations to finish
      Thread.sleep(5000)
      mongoClient.close()
    }
  }
}
