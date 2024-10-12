
# Proyecto Ajedrez con Poderes

Este proyecto es un juego de ajedrez con una variante especial que incluye poderes adicionales. Cada jugador puede seleccionar y aplicar poderes especiales a sus piezas o a las piezas de su oponente. Los poderes disponibles son:

- **Freeze**: Congela una pieza, impidiéndole moverse.
- **Proteger**: Protege una pieza de cualquier ataque.
- **Volar**: Permite que una pieza salte sobre otras piezas.

## Requisitos

Para ejecutar este proyecto, asegúrate de tener instalados(2 de ellos, Java Obligatorio):

- **Java**: Versión 17 LTS o superior (este proyecto utiliza Java 21).
- **JavaFX**: Versión 17 o superior (este proyecto utiliza JavaFX 23).
- **Maven**: Para gestionar dependencias y compilar el proyecto.

## Instalación y configuración

### Paso 1: Clonar el repositorio

Clona el proyecto desde el repositorio de GitHub o descarga los archivos directamente en tu sistema:

```bash
git clone https://github.com/salabes/tp1.git
cd tp1
```

### Paso 2: Configurar Java y JavaFX

Este proyecto utiliza JavaFX para la interfaz gráfica. Si ya tienes JavaFX instalado en tu sistema, asegúrate de agregar las rutas necesarias a tu entorno de desarrollo (IDE). Si no tienes JavaFX, puedes utilizar Maven para gestionar las dependencias.

#### Usar Maven para JavaFX (Recomendado)

El archivo `pom.xml` ya está configurado con las dependencias necesarias para JavaFX. No es necesario descargar manualmente JavaFX. Maven se encargará de ello.

### Dependencias de Maven
El proyecto cuenta con las siguientes dependencias incluidas en su archivo `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>${javafx.version}</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>${javafx.version}</version>
    </dependency>
</dependencies>
```

### Paso 3: Abrir el proyecto en IntelliJ IDEA

1. Abre IntelliJ IDEA.
2. Ve a `File > Open` y selecciona la carpeta del proyecto.
3. IntelliJ detectará automáticamente el archivo `pom.xml` y descargará las dependencias necesarias.

### Paso 4: Configurar el Path de JavaFX (opcional)

Si prefieres ejecutar el proyecto sin Maven y usas JavaFX instalado en tu sistema, asegúrate de configurar correctamente el `module-path` en IntelliJ:

1. Ve a `Run > Edit Configurations`.
2. En la sección de "VM Options", agrega las siguientes líneas, reemplazando `ruta/de/javafx` por la ruta donde está instalado JavaFX en tu sistema:

```bash
--module-path "ruta/de/javafx" --add-modules=javafx.controls,javafx.fxml
```

Si experimentas problemas de tamaño de ventana en pantallas de alta resolución, puedes agregar la opción adicional:

```bash
-Dprism.allowhidpi=false
```

## Compilación y ejecución

### Usando Maven (recomendado)

Para compilar y ejecutar el proyecto desde la terminal o desde IntelliJ usando Maven, ejecuta el siguiente comando:

```bash
mvn javafx:run
```

### Ejecutar desde IntelliJ

Si prefieres ejecutar el proyecto directamente desde IntelliJ, simplemente haz clic en el botón "Run" después de configurar el `module-path` de JavaFX si es necesario. Si ya configuraste Maven, también puedes hacer clic en "Execute Maven Goal" de Maven y clic en "mvn javafx:run".

## Instrucciones del Juego

### Inicio del Juego

1. **Ventana Inicial**: Al iniciar el programa, se abrirá una ventana con el título "Juego" y dos botones: 
   - **Jugar**
   - **Salir**

   Al presionar el botón **Jugar**, esta ventana desaparecerá y se abrirá una nueva ventana.

2. **Configuración de Jugadores**:
   En la nueva ventana, se pedirá ingresar los nombres de los jugadores (por defecto son "Jugador 1" y "Jugador 2"). Además, se mostrará un conjunto de **radio buttons** para que el Jugador 1 seleccione el color de las piezas (blancas o negras). El Jugador 2 automáticamente obtendrá el color opuesto.

   Una vez configurado, presiona el botón **Jugar** para continuar.

3. **Inicio del Tablero**:
   El tablero de ajedrez aparecerá en una nueva ventana, con las siguientes características:
   - **Nombres y tiempo** de los jugadores en la parte superior e inferior.
   - **Botones de poderes** a los lados del tablero:
     - **Freeze**: Congela una pieza del oponente.
     - **Proteger**: Proporciona protección a una pieza(no puede ser comida).
     - **Volar**: Permite que una pieza propia vuele (se mueva sobre otras).
   - **Efectos visuales** al aplicar los poderes:
     - Protección: Resplandor amarillo.
     - Volar: Resplandor azul.
     - Freeze: Opacidad reducida de la pieza.

4. **Botones adicionales**:
   - A los lados del tablero también se muestran los **contenedores de piezas comidas** por cada jugador.
   - En la parte inferior se encuentran los botones:
     - **Rendirse**
     - **Tablas**

5. **Final del Juego**:
   Si uno de los jugadores se rinde, se acaba el tiempo del contador o se acuerdan tablas, la ventana del tablero se cerrará y se abrirá una ventana final con el mensaje correspondiente:
   - "Ganaron las Blancas"
   - "Ganaron las Negras"
   - "Empate"

   Esta ventana final tendrá dos botones:
   - **Jugar**: Para reiniciar el juego desde el principio.
   - **Salir**: Para cerrar el programa por completo.

### Detalles Técnicos del Juego

- Los poderes duran **4 turnos** en general, excepto el poder **Volar**, que solo se aplica mientras la pieza realiza el movimiento.
- El jugador puede aplicar el poder a una pieza seleccionada haciendo clic en el botón del poder y luego en la pieza deseada.
- El **resplandor** alrededor de la pieza indica que un poder ha sido aplicado.
- Durante el turno de un jugador se puede mover la pieza y aplicar el poder que desea usar.


## Contribuir al proyecto

Si deseas contribuir al proyecto, crea una nueva rama en el repositorio, realiza tus cambios y envía una **pull request**. Todos los aportes son bienvenidos.
