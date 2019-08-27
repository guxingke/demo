# ANTLR4 Simple Sql To Mongo Shell Demo

## BUILD
### Build jar
```bash
mvn package
```

### Build Native Executable 
```bash
mvn package
sh build.sh

# gen target/sd
```
## Example
```bash
$ java -jar target/select-demo.jar 'select id,username from user where (_id <= 100 or _id >= 1000) and _id > 10 order by _id desc limit 10,10'
db.user.find({$and: [{$or: [{_id: {$lte: 100}},{_id: {$gte: 1000}}]},{_id: {$gt: 10}}]},{id:1, username:1}).sort({_id:-1}).skip(10).limit(10)
```
or
```bash
$ ./target/sd 'select id,username from user where (_id <= 100 or _id >= 1000) and _id > 10 order by _id desc limit 10,10'
db.user.find({$and: [{$or: [{_id: {$lte: 100}},{_id: {$gte: 1000}}]},{_id: {$gt: 10}}]},{id:1, username:1}).sort({_id:-1}).skip(10).limit(10)
```

## NOTE
just a demo .
