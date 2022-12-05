# École Polytechnique de l'Université de Tours - DI5
# TP 2 : JMH et les micro-benchmarks

# Objectifs
L'objectif de ce TP est de mettre en place des micro-benchmarks à l'aide d'un framework spécialisé : JMH.

# Matériels et logiciels nécessaires
Ces travaux pratiques se déroulent sur système GNU/Linux. Si vous travaillez sur votre machine personnelle,
il est de votre responsabilité d'y installer et configurer tous les logiciels nécessaires. Les machines de l'université fonctionnent sous la distribution ubuntu. Vous pouvez travailler sur d'autres distributions si vous le
souhaitez. Les versions du JDK que nous utilisons sont les versions 8, 9 et 11.

# Évaluation
L'évaluation de chaque TP s'effectue via compte-rendu écrit, seul ou en binôme, des travaux effectués durant
la séance de TP. Ce compte-rendu doit être fourni au format PDF pour chacun des TP. Vous pouvez utiliser
tous les outils que vous souhaitez pour mettre en forme votre compte-rendu : Word, Latex, LibreOffice,
Google doc... Le PDF doit être nommé comme ceci : **TPX_nomEtudiant1_nomEtudiant2.pdf**.

Dans le cas où le compte rendu PDF est accompagné d'autres documents (code, etc...), votre travail devra
être rendu sous la forme d'un fichier zip sous le nom **TPX_nomEtudiant1_nomEtudiant2.zip**. Un fichier
README dans le zip pour m'aider à comprendre le contenu de votre zip peut être le bienvenu.

L'objectif du compte rendu est de montrer que vous avez compris ce que vous faisiez, et que vous êtes capables d'avoir un regard critique. Prendre du recul sur les différents outils en allant plus loin que la simple
description de ceux-ci est très important. Votre compte rendu doit pouvoir être compris par n'importe qui.
De plus, si vous connaissez ou si vous rencontrez d'autres outils qui ne sont pas cités dans le cours, n'hésitez
pas à en parler aussi.

Le fond est important, mais la forme l'est tout autant. Votre compte rendu doit être "propre" et agréable à
lire. Il peut contenir des captures d'écran, des morceaux de code ou tout ce qui peut aider à étayer vos propos.
Une table des matières, une brève introduction, des parties clairement définies ainsi qu'une conclusion sont
les bienvenues.

Concernant les délais, le compte-rendu doit être rendu au plus tard une semaine après la dernière séance du
TP sur la plateforme Celene dédiée à ce cours. La date limite sera aussi précisée sur le cours. Le mot de passe
vous sera fourni lors des séances.

Bien entendu, je serai présent lors des TP, n'hésitez donc pas à me poser des questions si vous en avez besoin. Vous pouvez aussi me contacter par mail (jules.delarue@soprasteria.com / jules.delarue@univ-tours.fr) entre deux séances en cas de
problème.

Il n'existe pas une seule et unique correction possible pour ce TP. De ce fait, aucune correction ne vous sera
fournie. Cependant, si vous souhaitez avoir des informations/précisions sur votre compte rendu, n'hésitez
pas à me contacter soit par mail, soit directement pendant les séances suivantes pour que nous puissions en
discuter.

# Génération et lancement du projet JMH

## Vérification des prérequis

⚠️Si vous utilisez les VM présentes sur le réseau, ne prenez pas celle de Florent Clarret, **prenez la VM Ubuntu 18.04 Développement**

### Java
```shell
sudo apt-get install openjdk-11-jdk
```


```shell
java --version
```

### Maven
```shell
sudo apt install maven
```
```shell
mvn --version
```

## Initialisation d'un nouveau projet maven.
La commande suivante permet de générer un nouveau projet maven avec la génération automatique d'un fichier vide MyBenchmark.java pouvant servir de point d'entrée au TP
```shell
mvn archetype:generate -DinteractiveMode=false -DarchetypeGroupId=org.openjdk.jmh -DarchetypeArtifactId=jmh-java-benchmark-archetype -DgroupId=fr.polytechtours.javaperformance.tp2 -DartifactId=jmh -Dversion=1.0.0
```
Syntaxe Windows
```shell
mvn archetype:generate "-DinteractiveMode=false" "-DarchetypeGroupId=org.openjdk.jmh" "-DarchetypeArtifactId=jmh-java-benchmark-archetype" "-DgroupId=fr.polytechtours.javaperformance.tp2" "-DartifactId=jmh" "-Dversion=1.0.0"
```
cf. https://mvnrepository.com/artifact/org.openjdk.jmh/jmh-java-benchmark-archetype


Comme pour tout projet maven, vous pouvez construire le jar à l'aide de la commande suivante

⚠️La commande suivante est a exécuter à l'endroit où se trouve le pom.xml
```shell
mvn clean install
```

Enfin, vous pouvez lancer les benchmarks à l'aide de la commande suivante (peut varier selon les paramètres précisés lors de la génération du projet).
```shell
java -jar target/benchmarks.jar
```

À la fin du benchmark, vous obtiendrez un rapport ressemblant au suivant
```text
Benchmark                       Mode  Cnt      Score     Error  Units
MyBenchmark.testStringConcat1  thrpt    5   3079,574 ±  88,816  ops/s
MyBenchmark.testStringConcat2  thrpt    5  92716,073 ± 347,134  ops/s
```

Selon les paramètres, le benchmark peut-être très long. Ici, il dure de 10 à 15 minutes. En effet, il effectue 20
phases de warmup d'une seconde puis lance 20 itérations de tests, le tout 10 fois par test. Ici, on lui demande
de nous afficher le nombre d'opérations qu'il a réussi à exécuter par seconde. Ce sont les paramètres par
défaut de JMH.
Note : Il existe des plugins pour certains IDE permettant de lancer un benchmark directement via son
interface graphique. Vous pouvez l'utiliser si vous le souhaitez, cependant il est recommandé de savoir se
servir de JMH en ligne de commande.

# Etude de JMH
Avant de vous lancer dans l'écriture d'un micro-benchmark, il vous est demandé de lire la documentation
associée à JMH. Vous constaterez que JMH est très simple d'utilisation et qu'il repose en grande partie
sur l'utilisation d'annotations. Parmi les plus importantes, il vous est demandé de décrire les suivantes :
- Benchmark
- BenchmarkMode
- Fork
- Group
- GroupThread
- Measurement
- OutputTimeUnit
- State
- TearDown
- Timeout
- Warmup
- CompilerControl

Il existe d'autres annotations, vous avez la possibilité de
les décrire si vous le souhaitez. En plus de cela, des classes utilitaires sont disponibles telles que BlackHole
par exemple. Il peut être utile de regarder les options disponibles au niveau de la JVM, par exemple CompileCommand.
En complément, il est demandé de décrire les paramètres possibles à fournir à JMH ainsi que ce que signifie le
bilan final affiché par celui-ci : comment doit-on le comprendre ? Comment déterminer quelle implémentation
est la plus rapide ? Etc...

Si certaines annotations semblent obscures, vous pouvez les laisser de côté dans un premier temps et illustrer leur fonctionnement par la rédaction d'un micro-benchmark adapté.

# Mise en place d'un micro-benchmark
Maintenant que vous savez comment générer un projet JMH, il vous est demandé de mettre en place un
micro-benchmark afin de comparer deux (ou plus) méthodes qui réalisent la même chose d'un point de vue
fonctionnel, mais développé de manière différente afin de confronter les performances. Il n'est pas forcément
nécessaire de comparer des méthodes complexes pour se rendre compte de l'utilité de JMH, vous pouvez
rester sur des méthodes simples. Voici une liste d'idées que vous pouvez mettre en place :
- StringBuffer vs StringBuilder
- StringBuilder vs String.concat
- String.replace vs StringUtils.replace
- StringUtils.trim vs String.trim
- N'importe quelle implémentation A d'un algorithme vs une implémentation B
  de celui-ci. On peut par exemple penser au calcul de la suite de Fibonacci, de la factorielle... Soyez
  créatifs !

Notez cependant qu'il est préférable de choisir la dernière option, à savoir confronter deux implémentations
d'un algorithme que vous connaissez. ~~Si vous n'avez pas d'idée, ou pas de code sous la main, vous pouvez
utiliser les différentes implémentations de tris que j'ai placé sur l'espace sur célène.~~

N'hésitez pas à multiplier les tests avec des paramètres différents. Par exemple, si vous travaillez avec des
chaînes de caractères, faites des tests avec des chaînes très courtes, d'autres avec des tailles plus grandes.
L'objectif et de pouvoir comparer deux méthodes le plus objectivement possible, en prenant en compte tous
les paramètres possibles. De plus, vous pouvez créer plusieurs classes de tests afin de jouer avec les paramètres
de JMH ou de la JVM en elle-même. Vous êtes libres de tester autant de possibilités que vous le souhaitez.
Vous devrez présenter dans votre rapport les différents résultats que vous avez obtenus en essayant de les
expliquer.


# Optionnel : Analyse des méthodes testées
Si vous le souhaitez, vous pouvez aussi utiliser les outils vus lors du premier TP (jvisualvm, java mission
control, etc.) sur les méthodes que vous avez testées afin d'essayer de comprendre les raisons qui font qu'une
méthode est plus efficace qu'une autre.


