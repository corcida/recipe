# Recipe

Aplicación de demostración de habilidades basada en un listado de recetas, detalle con su información y mapa marcando origen de la misma.

# Features

1. Kotlin
1. Clean architecture
1. MVVM
1. Compose navigation 
1. Retrofit para conexiones http
1. Room database 
1. Use cases
1. Unit Tests
1. Kotlin Flow
1. Inyección de dependencias mediante Hilt
1. Manejo de dependencias con Kotlin KTS


# Arquitectura

## Clean architecture

El proyecto está construido bajo el marco de la clean architecture, dividido en 5 capas:

- Domain: Contiene todo los modelos de negocio, en este caso particular, la clase Recipe y Location.
- Data: En esta capa se encuentra una definición abstracta de las diferentes fuentes de datos, y la forma en que se debe utilizar. Usando un patrón de diseño de repositorio que, para una determinada solicitud, es capaz de decidir dónde encontrar la información.
- Use cases: Capa que interactúa con la capa de data, definiendo así los procesos que deben ser realizados y pasos para hacer a los mismos. Es también la capa a la cual tiene acceso nuestra capa de presentación desde los view models.
- Framework: Primera capa ya perteneciente a nuestro módulo android, y la misma define las implementaciones de los local source y remote data source provenientes de la capa de data.
- Presentation (UI): Segunda capa de nuestro módulo android, y en esta es donde definimos nuestras pantallas, view models y navegación, accediendo a la data mediante nuestros use cases. 

## MVVM

Se decidió usar la arquitectura para la capa de presentación de MVVM, siendo la recomendada por Google y que apunta en sus guías de diseño. La comunicación entre el View Model y el Modelo fue mediante nuestros use case inyectados al constructor mediante Hilt, 
y con respecto a la comunicación View Model - View, fue basada en los state flow del tipo de dato correspondiente para cada contexto particular.

# UI

Para la construcción de la UI se decidió utilizar a Jetpack Compose como herramienta, siendo esta la recomendada por Google y siguiendo la consigna de brindar lo más moderno de la plataforma en la construcción de esta app.

La navegación es construida en base a compose y es creada mediante una sola Activity. La carga de imágenes es gracias a Coil. Y se siguieron las guías de diseño para brindar una app preparada para ser vista tanto en modo oscuro como claro.

## Screens

- HomeScreen: Punto de partida de la app, y en esta se carga el listado de recetas en un LazyVerticalGrid, permitiendo visualizar la imagen, título y si es o no una receta favorita para el usuario. También da la opción de buscar en el TextField superior, por nombre o ingrediente de la receta.
- DetailScreen: Pantalla que despliega la información de la receta y permite tanto volver al listado, como acceder al mapa de origen.
- MapScreen: Despliga un mapa con un Marker en el punto de origen aproximado de la receta en cuestión. 

# Principales librerías

## Data

En la capa de framwork se sustentan los archivos de implementación en las librerías de Retrofit para el caso de la data de fuente remota, y de Room para la data local. 

La app basa como single source of truth, a la información que proviene de la base de datos local, por lo tanto, la data con la que interactúa el usuario es únicamente la de la base de datos. Esto gracias al flow de data proveniente de room, dando como resultado una capa de datos reactiva.


## Inyección de dependencias

La inyección de dependencias, necesaria para la escalabilidad del proyecto y facilitamiento a la hora del testing, se realizó gracias a Hilt, mediante la construcción de 3 módulos, los cuales son:

- AppModule: Creando todo lo correspondiente a la capa de Framework de la app.
- DataModule: Definiendo el repositorio de recipe de la app.
- UseCasesModule: Encargado de construir todos los casos de uso los cuales son inyectados luego a nuestros view models.

# Testing

Para el testing se decidió crear test unitarios a nuestros casos de uso y view models, debido a que en estas clases se encontraba la mayor cantidad de lógica de negocio de nuestra app. Esto gracias a librerías como Junit en la construcción de los test y Mockito, para crear mocks de las dependencias de cada una de las clases bajo prueba.

Los mismos pueden ser probados en las task del gradle como: gradle app:testDebugUnitTest y gradle usecases:test








