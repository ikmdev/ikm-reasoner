# ELK Reasoner for IKM

This is a fork of the [ELK Reasoner](https://github.com/liveontologies/elk-reasoner)

Primary changes:

* Implement modules (JPMS)
* Remove some modules including proofs (and puli support)
* Replace guava with java.util and Eclipse collections
* Replace custom collections with Eclipse collections
* Addition of SNOMED POJO model, reasoner, and parser (without dependency on the OWLAPI)

Requires Java 21.

To build on Unix/Linux/OSX: `./mvnw clean install`

On Windows: `./mvnw.cmd clean install`

To run the integration tests:

```
./mvnw clean install -DskipITs=false
```

The integration tests require the SNOMED test data artifacts from the [reasoner-test-data project](https://github.com/ikmdev/reasoner-test-data)

Available at [Maven Central](https://central.sonatype.com/namespace/dev.ikm.elk)

### Team Ownership - Product Owner

Data Team - Eric Mays (External) <emays@mays-systems.com>

## Issues and Contributions
Technical and non-technical issues can be reported to the [Issue Tracker](https://github.com/ikmdev/ikm-reasoner/issues).

Contributions can be submitted via pull requests. Please check the [contribution guide](doc/how-to-contribute.md) for more details.
