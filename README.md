# Mobility Health

Mobility Health est une application Android conçue pour améliorer la santé et la mobilité des utilisateurs. Ce projet utilise Kotlin, Compose, Hilt, et d'autres bibliothèques modernes pour offrir une expérience utilisateur fluide et performante.

## Prérequis

- Android Studio
- JDK 11 ou supérieur
- Gradle 8.10.2

## Installation

1. Clonez le dépôt :
    ```sh
    git clone https://github.com/votre-utilisateur/mobility-health.git
    cd mobility-health
    ```

2. Ouvrez le projet dans Android Studio.

3. Synchronisez le projet avec Gradle en utilisant le fichier `build.gradle.kts`.

## Structure du projet

- `app/` : Contient le code source de l'application.
- `gradle/` : Contient les fichiers de configuration Gradle.
- `.idea/` : Contient les fichiers de configuration spécifiques à l'IDE.
- `build.gradle.kts` : Fichier de configuration Gradle principal.
- `settings.gradle.kts` : Fichier de configuration des paramètres Gradle.

## Configuration

Le fichier `gradle/wrapper/gradle-wrapper.properties` spécifie la version de Gradle utilisée par le projet :
```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.10.2-bin.zip