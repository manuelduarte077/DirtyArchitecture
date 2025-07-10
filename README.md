# Dirty Architecture

Este repositorio contiene una colección de aplicaciones Android que demuestran diferentes implementaciones de arquitectura limpia (Clean Architecture) y patrones de diseño modernos en el desarrollo de aplicaciones móviles.

## Proyectos incluidos

### 1. BookLibrary
Una aplicación multiplataforma Kotlin (KMP) para Android e iOS que implementa una biblioteca de libros.

- **Tecnologías**: Kotlin Multiplatform, Compose Multiplatform
- **Estructura**: Organizada en módulos para código compartido y específico de plataforma

### 2. DirtyMorty
Una aplicación Android que muestra información sobre personajes de la serie "Rick and Morty" utilizando Clean Architecture y el patrón MVI.

- **Módulos**:
  - `app`: Módulo principal de la aplicación
  - `core`: Componentes compartidos y UI Kit
  - `domain`: Casos de uso y repositorios
  - `data`: Acceso a datos (API y almacenamiento local)
  - `presentation`: Módulos de UI para lista de personajes y detalles

- **Bibliotecas**: Retrofit, OkHttp, Kotlinx Serialization, Coil, Hilt, Jetpack Compose

### 3. StarWars
Aplicación Android con temática de Star Wars.

- **Tecnologías**: Kotlin, Jetpack Compose

### 4. TodoApp
Aplicación multiplataforma de lista de tareas para Android, iOS y Desktop.

- **Tecnologías**: Kotlin Multiplatform, Compose Multiplatform

### 5. WeatherApp
Aplicación de pronóstico del tiempo para Android.

- **Tecnologías**: Kotlin, Jetpack Compose

## Características comunes

- **Jetpack Compose** para una UI moderna y declarativa
- **MVVM + Clean Architecture** para código escalable y mantenible
- **Retrofit** para peticiones de red
- **Room** para almacenamiento local de datos
- **Dagger Hilt** para inyección de dependencias
- **Coroutines & Flow** para programación asíncrona eficiente
- **Unit & UI Testing** con JUnit, Mockito y Robolectric