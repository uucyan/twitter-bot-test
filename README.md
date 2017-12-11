# twitter-bot-test
https://twitter.com/gbp_bot_test

# Spring Boot + KotlinでTwitterのボットを作る

## 目標
Spring Boot + Kotlinで動く、数分おきにツイートするTwitterのボットを実装します。

最終的に、実装したTwitterボットをHerokuにデプロイして動かすところまでが目標です。

## 実装のポイント
- Spring Cloud Taskでバッチ処理の実装
- Spring Social Twitterで楽々ツイッター操作
- ついでにMySQLとの連携でツイートのログを保存

## 手順
1. SPRING INITIALIZRでProject作成
2. Gladleの設定
3. Twitter Application ManagementでAPI Keysの取得
4. バッチ処理の実装
5. Twitterとの連携＆ツイート処理の実装
6. MySQLとの連携
7. Herokuにデプロイ

やること多いように思いますが、一つ一つの手順はとても簡単です。

## 環境
- OS：macOS High Sierra 10.13.1
- エディタ：ATOM 1.22.1（IntelliJ？そんなエディタ知りません）
- ターミナル：iTerm2 3.1.5.Bata.2（Bata使ってたことにここで初めて気づく）

IntelliJなら、Spring BootのProjectをエディタ上で作れる。

## SPRING INITIALIZRでProject作成
まずはSpring Bootのプロジェクトフォルダを作成します。

[SPRING INITIALIZR](https://start.spring.io) にアクセスし、画面上側にあるプルダウンを以下のように選択します。
```
Generate a [Gradle Project] with [Kotlin] and Spring Boot [1.5.9]
```

次に、Project Metadataを設定します。

ここは自由に命名してもらってかまいません。

| Group    | Artifact |
|:---------|:---------|
| 生成されるソースのパッケージ名。 | アプリケーションの名前。ディレクトリ名に使われる。 |

最後に、Dependencies(依存ライブラリ)を設定します。

検索ワードを入力すると、依存ライブラリの候補が表示されるので、以下のものを選択する。

| 検索ワード | 選択項目 | 何に使うか |
|:-----------|:---------|:---------|
| cloud task | Cloud Task | バッチ処理の実行
| twitter | Twitter | Twitterとの連携 |
| jpa | JPA | MySQLとの連携 |

上記内容の設定がすべて終わったら、「Generate Project」ボタンでプロジェクトフォルダをzipファイルでダウンロードします。

zipファイルを解凍してお好みのディレクトリに配置してください。

## Gladleの設定
良い感じになるように、build.gradleファイルを編集します。


Spring Boot実行時のコンソールに色をつけてくれる。
```
bootRun {
	jvmArgs = ['-Dspring.output.ansi.enabled=always']
}
```

Kotlinのビルド時にJPAを扱うのに必要なライブラリ（多分）。
```
apply plugin: 'kotlin-jpa'
```

MySQLとの接続に必要。
```
compile('mysql:mysql-connector-java')
```

Kotlinのライブラリ。
dataクラスで初期値を定義しなくて済む。
```
classpath("org.jetbrains.kotlin:kotlin-noarg:${kotlinVersion}")

noArg {
	annotation('javax.persistence.Table')
}
```

Kotlinのライブラリ。
日付系の処理が楽になる。
```
compile('me.mattak:moment:0.0.4')
```

MySQLはまだ使わないので、下記の部分を一旦コメントアウト。
※DBの設定ファイルがない状態でビルドするとエラーになる
```
// compile('org.springframework.boot:spring-boot-starter-data-jpa')
```

全部足した版がこんな感じ
```
buildscript {
	ext {
		kotlinVersion = '1.1.61'
		springBootVersion = '1.5.9.RELEASE'
	}
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
		classpath("org.jetbrains.kotlin:kotlin-allopen:${kotlinVersion}")
		classpath("org.jetbrains.kotlin:kotlin-noarg:${kotlinVersion}")
	}
}

apply plugin: 'kotlin'
apply plugin: 'kotlin-spring'
apply plugin: 'kotlin-jpa'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'

group = 'test-bot'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = 1.8
compileKotlin {
	kotlinOptions.jvmTarget = "1.8"
}
compileTestKotlin {
	kotlinOptions.jvmTarget = "1.8"
}

repositories {
	mavenCentral()
}


ext {
	springCloudTaskVersion = '1.2.2.RELEASE'
}

dependencies {
	compile('org.springframework.cloud:spring-cloud-starter-task')
	// compile('org.springframework.boot:spring-boot-starter-data-jpa')
	compile('org.springframework.boot:spring-boot-starter-social-twitter')
	compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:${kotlinVersion}")
	compile("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
	compile('mysql:mysql-connector-java')
	compile('me.mattak:moment:0.0.4')
	testCompile('org.springframework.boot:spring-boot-starter-test')
}

dependencyManagement {
	imports {
		mavenBom "org.springframework.cloud:spring-cloud-task-dependencies:${springCloudTaskVersion}"
	}
}

noArg {
	annotation('javax.persistence.Table')
}

bootRun {
	jvmArgs = ['-Dspring.output.ansi.enabled=always']
}

```

ここまできたら、 `./gradlew bootRun` で一度ビルドして実行してみましょう！

こんな感じになったら成功です。
```
Uucyan:twitter-bot-test$ ./gradlew bootRun
:compileKotlin
:compileJava NO-SOURCE
:copyMainKotlinClasses UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:findMainClass
:bootRun
02:03:08.589 [main] DEBUG org.springframework.boot.devtools.settings.DevToolsSettings - Included patterns for restart : []
02:03:08.592 [main] DEBUG org.springframework.boot.devtools.settings.DevToolsSettings - Excluded patterns for restart : [/spring-boot-starter/target/classes/, /spring-boot-autoconfigure/target/classes/, /spring-boot-starter-[\w-]+/, /spring-boot/target/classes/, /spring-boot-actuator/target/classes/, /spring-boot-devtools/target/classes/]
02:03:08.593 [main] DEBUG org.springframework.boot.devtools.restart.ChangeableUrls - Matching URLs for reloading : [file:/Users/R-Hashimoto/Desktop/twitter-bot-test/build/classes/main/, file:/Users/R-Hashimoto/Desktop/twitter-bot-test/build/resources/main/]

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.9.RELEASE)

2017-12-12 02:03:09.677  INFO 40324 --- [  restartedMain] t.t.TwitterBotTestApplicationKt          : Starting TwitterBotTestApplicationKt on Lunako-Air.local with PID 40324 (/Users/R-Hashimoto/Desktop/twitter-bot-test/build/classes/main started by R-Hashimoto in /Users/R-Hashimoto/Desktop/twitter-bot-test)
2017-12-12 02:03:09.678  INFO 40324 --- [  restartedMain] t.t.TwitterBotTestApplicationKt          : No active profile set, falling back to default profiles: default
2017-12-12 02:03:09.849  INFO 40324 --- [  restartedMain] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@32ed1eb7: startup date [Tue Dec 12 02:03:09 JST 2017]; root of context hierarchy
2017-12-12 02:03:12.023  INFO 40324 --- [  restartedMain] f.a.AutowiredAnnotationBeanPostProcessor : JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
2017-12-12 02:03:13.328  INFO 40324 --- [  restartedMain] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-12-12 02:03:13.365  INFO 40324 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2017-12-12 02:03:13.368  INFO 40324 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.23
2017-12-12 02:03:13.573  INFO 40324 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-12-12 02:03:13.573  INFO 40324 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 3726 msn
2017-12-12 02:03:13.796  INFO 40324 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-12-12 02:03:13.805  INFO 40324 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-12-12 02:03:13.806  INFO 40324 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-12-12 02:03:13.806  INFO 40324 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-12-12 02:03:13.806  INFO 40324 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-12-12 02:03:14.475  INFO 40324 --- [  restartedMain] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@32ed1eb7: startup date [Tue Dec 12 02:03:09 JST 2017]; root of context hierarchy
2017-12-12 02:03:14.687  INFO 40324 --- [  restartedMain] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-12-12 02:03:14.689  INFO 40324 --- [  restartedMain] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-12-12 02:03:14.740  INFO 40324 --- [  restartedMain] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-12 02:03:14.740  INFO 40324 --- [  restartedMain] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-12 02:03:14.810  INFO 40324 --- [  restartedMain] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-12 02:03:15.113  INFO 40324 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2017-12-12 02:03:15.199  INFO 40324 --- [  restartedMain] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-12-12 02:03:15.315  INFO 40324 --- [  restartedMain] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-12-12 02:03:15.323  INFO 40324 --- [  restartedMain] t.t.TwitterBotTestApplicationKt          : Started TwitterBotTestApplicationKt in 6.707 seconds (JVM running for 7.61)
<===========--> 85% EXECUTING
> :bootRun
```

Ctrl + C で停止。

## Twitter Application ManagementでAPI Keysの取得
ボットに使うTwitterアカウントに接続するための情報を事前に入手しておきます。

ここの手順はググればいくらでも出るのでさらっといくよ！

後で書く。
