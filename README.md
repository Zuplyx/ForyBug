# Apache Fory Serialization Bug

This project serves to demonstrate an [Issue](https://github.com/apache/fory/issues/3118) in the Serialization of Apache Fory>0.12.3

## Usage

Simply run ``./gradlew run``.
You will get an exception like this

````
java.lang.RuntimeException: Create sequential serializer failed, 
class: class schemacrawler.crawl.MutableTableConstraintColumn
	at org.apache.fory.serializer.CodegenSerializer.loadCodegenSerializer(CodegenSerializer.java:52)
	at org.apache.fory.resolver.ClassResolver.lambda$getObjectSerializerClass$3(ClassResolver.java:1158)
	at org.apache.fory.builder.JITContext.registerSerializerJITCallback(JITContext.java:121)
	at org.apache.fory.resolver.ClassResolver.getObjectSerializerClass(ClassResolver.java:1156)
	at org.apache.fory.resolver.ClassResolver.getSerializerClass(ClassResolver.java:1110)
	at org.apache.fory.resolver.ClassResolver.getSerializerClass(ClassResolver.java:988)
	at org.apache.fory.resolver.ClassResolver.createSerializer(ClassResolver.java:1377)
	at org.apache.fory.resolver.ClassResolver.getClassInfo(ClassResolver.java:1250)
	at schemacrawler.crawl.NamedObjectListForyRefCodec_0.writeChunk1$(NamedObjectListForyRefCodec_0.java:168)
	at schemacrawler.crawl.NamedObjectListForyRefCodec_0.writeFields1$(NamedObjectListForyRefCodec_0.java:218)
	at schemacrawler.crawl.NamedObjectListForyRefCodec_0.write(NamedObjectListForyRefCodec_0.java:433)
	at schemacrawler.crawl.MutablePrimaryKeyForyRefCodec_0.writeFields$(MutablePrimaryKeyForyRefCodec_0.java:99)
	at schemacrawler.crawl.MutablePrimaryKeyForyRefCodec_0.write(MutablePrimaryKeyForyRefCodec_0.java:397)
	at schemacrawler.crawl.MutableTableForyRefCodec_0.writeFields$(MutableTableForyRefCodec_0.java:109)
	at schemacrawler.crawl.MutableTableForyRefCodec_0.write(MutableTableForyRefCodec_0.java:612)
	at schemacrawler.crawl.NamedObjectListForyRefCodec_0.writeChunk1$(NamedObjectListForyRefCodec_0.java:188)
	at schemacrawler.crawl.NamedObjectListForyRefCodec_0.writeFields1$(NamedObjectListForyRefCodec_0.java:218)
	at schemacrawler.crawl.NamedObjectListForyRefCodec_0.write(NamedObjectListForyRefCodec_0.java:433)
	at schemacrawler.crawl.MutableCatalogForyRefCodec_0.writeFields1$(MutableCatalogForyRefCodec_0.java:138)
	at schemacrawler.crawl.MutableCatalogForyRefCodec_0.write(MutableCatalogForyRefCodec_0.java:416)
	at org.apache.fory.Fory.writeData(Fory.java:654)
	at org.apache.fory.Fory.write(Fory.java:405)
	at org.apache.fory.Fory.serialize(Fory.java:325)
	... 3 more
Caused by: java.lang.RuntimeException: org.apache.fory.codegen.CodegenException: Compile error: 
````

If the version of Fory is changed back to 0.12.3 in [libs.versions.toml](gradle/libs.versions.toml) the serialization
completes without issues.
