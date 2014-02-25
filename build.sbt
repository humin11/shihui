name := "shihui"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  Resolver.url("play-authenticate (release)", url("http://joscha.github.com/play-authenticate/repo/releases/"))(Resolver.ivyStylePatterns),
  Resolver.url("play-authenticate (snapshot)", url("http://joscha.github.com/play-authenticate/repo/snapshots/"))(Resolver.ivyStylePatterns),
  Resolver.url("play-easymail (release)", url("http://joscha.github.com/play-easymail/repo/releases/"))(Resolver.ivyStylePatterns),
  Resolver.url("play-easymail (snapshot)", url("http://joscha.github.com/play-easymail/repo/snapshots/"))(Resolver.ivyStylePatterns),
  Resolver.url("Objectify Play Repository", url("http://schaloner.github.io/releases/"))(Resolver.ivyStylePatterns),
  Resolver.url("Objectify Play Snapshot Repository", url("http://schaloner.github.io/snapshots/"))(Resolver.ivyStylePatterns)
)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "be.objectify" %% "deadbolt-java" % "2.2.1-RC2",
  "mysql" % "mysql-connector-java" % "5.1.28",
  "ws.securesocial" %% "securesocial" % "2.1.3",
  "com.feth" %% "play-authenticate" % "0.5.0-SNAPSHOT",
  "com.feth" %% "play-easymail" % "0.5-SNAPSHOT"
)

play.Project.playJavaSettings
