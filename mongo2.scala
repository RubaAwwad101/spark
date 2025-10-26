
import com.mongodb.{ServerApi, ServerApiVersion}
import org.mongodb.scala.{ConnectionString, MongoClient, MongoClientSettings}
import org.mongodb.scala.bson.Document
import org.mongodb.scala.model.Filters.equal

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.util.Using

object mongo2 {

  def main(args: Array[String]): Unit = {

    val userName = "rubaawwad"
    val password = "401610357"
    val connectionString = s"mongodb+srv://$userName:$password@cluster0.euoitdu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

    // Construct a ServerApi instance using the ServerApi.builder() method
    val serverApi = ServerApi.builder.version(ServerApiVersion.V1).build()

    val settings = MongoClientSettings
      .builder()
      .applyConnectionString(ConnectionString(connectionString))
      .serverApi(serverApi)
      .build()

    // Create a new client and connect to the server
    Using(MongoClient(settings)) { mongoClient =>
      // Send a ping to confirm a successful connection
      val database = mongoClient.getDatabase("Sample_Resturant")
      val collection = database.getCollection("Resturants")

      // Find a restaurant by name
      collection.find(equal("name", "Brunos On The Boulevard"))
        .subscribe(
          (doc: Document) => println(s"📄 Found: ${doc.toJson()}"),
          (e: Throwable) => println(s"❌ Query error: $e"),
          () => println("✔️ Query completed.")
        )
      val ping = database.runCommand(Document("ping" -> 1)).head()

      Await.result(ping, 10.seconds)
      System.out.println("Pinged your deployment. You successfully connected to MongoDB!")
    }

  }
}
