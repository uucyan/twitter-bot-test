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

続きは後で書く。
