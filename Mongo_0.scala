import org.mongodb.scala.MongoClient
import org.mongodb.scala.bson.{BsonArray, BsonDocument, BsonDouble, BsonString}
import org.mongodb.scala.bson.conversions.Bson

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Mongo_0 extends App {

   val mongoUrl = "mongodb://localhost:27017/?minPool=2&maxPool=10"
  val mongoClient = MongoClient(mongoUrl)
  val dataBase = mongoClient.getDatabase(name="myDB")
  Await.result(dataBase.createCollection(collectionName = "chefs").toFuture(),100 seconds)
  val collection = dataBase.getCollection(collectionName = "chefs")


  val insertDocument =new BsonDocument()
  insertDocument.append("FirstName",BsonString("Awwad"))
    .append("LastName",BsonString("Awwad"))
    .append("Address",BsonArray(BsonString("Tulkarm"),BsonString("irtah")))
    .append("Experinces",BsonDouble(5.5))

  Await.result(collection.insertOne(insertDocument).toFuture(),100 seconds)
  val result= Await.result(collection.find().toFuture(),100 seconds)
  println(result)
  mongoClient.close()


}
