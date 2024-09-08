# Resumen del problema

El objetivo de este challenge es desarrollar un programa que detecte si una persona es mutante basándose en su secuencia de ADN, creando una API que se pueda usar para verificar esto. A continuación se explica cómo abordar este problema, incluyendo la implementación del algoritmo, creación de la API, diseño de base de datos, y todo lo que se debe entregar.

### 1. Desafío 1: Programa que detecta mutantes

>```java
>boolean isMutant(String[] dna);
>```

Este programa debe analizar una secuencia de ADN representada como una matriz NxN donde cada elemento puede ser A, T, C o G. El objetivo es determinar si hay más de una secuencia de cuatro letras idénticas en las direcciones horizontales, verticales u oblicuas.

|  A  |  T  |  G  |  C  |  G  |  A  |
|:---:|:---:|:---:|:---:|:---:|:---:|
|  C  |  A  |  G  |  T  |  G  |  C  |
|  T  |  T  |  A  |  T  |  G  |  T  |
|  A  |  G  |  A  |  A  |  G  |  G  |
|  C  |  C  |  C  |  C  |  T  |  A  |
|  T  |  C  |  A  |  C  |  T  |  G  |

### Solución en Java

* Se crea una clase llamada *MutantUtil*, está diseñada para determinar si una secuencia de ADN pertenece a un mutante, según un criterio específico. A continuación, te explico cada parte:

#### Constantes

* MUTANT_SEQUENCE_LENGTH: Esta constante define la longitud mínima de la secuencia de ADN repetida que se considera mutante. En este caso, se establece en 4.
>```java
>private static final int MUTANT_SEQUENCE_LENGTH = 4;
>```

* DETECT_DIRECTION : Este array bidimensional define las direcciones en las que se buscarán secuencias repetidas en la matriz de ADN. Las direcciones son:
  - Horizontal: {0, 1}
  - Vertical: {1, 0}
  - Diagonal principal (de arriba-izquierda a abajo-derecha): {1, 1}
  - Diagonal secundaria (de arriba-derecha a abajo-izquierda): {1, -1}
>```java
>private static final int[][] DETECT_DIRECTION = {
> {0, 1},
> {1, 0},
> {1, 1},
> {1, -1}
>};
>```

#### Método isMutantValid
Este es el método principal que verifica si el ADN pertenece a un mutante.

1. Parámetro: Recibe un array de String[] que representa las cadenas de ADN.
2. Variables Locales:
   * sizeAdn: Guarda el tamaño del array de ADN, que también representa la longitud de cada cadena de ADN, asumiendo que la matriz es cuadrada (NxN).
   * sequenceCount: Cuenta cuántas secuencias de ADN mutantes han sido detectadas
3. Bucles Anidados:
   * El primer bucle recorre cada fila del ADN. 
   * El segundo bucle recorre cada columna del ADN. 
   * El tercer bucle itera sobre las cuatro direcciones definidas en DETECT_DIRECTION.
4. Verificación de Secuencias:
   * Para cada celda (es decir, cada posición del ADN), llama al método isSequence para verificar si hay una secuencia repetida de longitud 4 en alguna dirección.
   * Si encuentra una secuencia, incrementa sequenceCount.
   * Si encuentra al menos 2 secuencias, el ADN se considera mutante, y el método devuelve true.
5. Resultado Final:
   * Si no se detectan al menos 2 secuencias repetidas, devuelve false, indicando que el ADN no es mutante.

#### Método isSequence
Este método privado verifica si hay una secuencia repetida de longitud MUTANT_SEQUENCE_LENGTH comenzando desde una posición específica en la dirección dada.

1. Parámetros:
   * dna: El array de cadenas de ADN. 
   * row y col: La posición de inicio en la matriz de ADN. 
   * direction: La dirección en la que se buscará la secuencia repetida (horizontal, vertical o diagonal).
2. Lógica:
   * Guarda el valor de la letra en la posición inicial (row, col) en baseLetter. 
   * Recorre las siguientes posiciones en la dirección especificada para ver si las letras coinciden con baseLetter. 
   * Si alguna posición está fuera de los límites de la matriz o si la letra en esa posición no coincide, devuelve false. 
   * Si todas las letras coinciden, devuelve true, indicando que se ha encontrado una secuencia válida.

#### Código
>```java
>public class MutantUtil {
> private static final int MUTANT_SEQUENCE_LENGTH = 4;
> private static final int[][] DETECT_DIRECTION = {
>            {0, 1},   // Horizontal
>            {1, 0},   // Vertical
>            {1, 1},   // Diagonal principal
>            {1, -1}   // Diagonal secundaria
>    };
>
>    public boolean isMutantValid(String[] dna) {
>        int sizeAdn = dna.length;
>        int sequenceCount = 0;
>
>       for (int i = 0; i < sizeAdn; i++) {
>          for (int j = 0; j < sizeAdn; j++) {
>                for (int[] direction : DETECT_DIRECTION) {
>                    if (isSequence(dna, i, j, direction)) {
>                        sequenceCount++;
>                        if (sequenceCount >= 2) {
>                            return true;
>                        }
>                    }
>                }
>            }
>        }
>        return false;
>    }
>
>    private boolean isSequence(String[] dna, int row, int col, int[] direction) {
>        char baseLetter = dna[row].charAt(col);
>        int newRow, newCol;
>
>        for (int i = 1; i < MUTANT_SEQUENCE_LENGTH; i++) {
>            newRow = row + i * direction[0];
>            newCol = col + i * direction[1];
>
>            if (newRow >= dna.length || newRow < 0 || newCol >= dna.length || newCol < 0) {
>                return false;
>            }
>
>            if (dna[newRow].charAt(newCol) != baseLetter) {
>                return false;
>            }
>        }
>
>        return true;
>    }
>}
>>```

### 2. Desafío 2: API REST
Para este desafío, implementarás dos API REST en tu aplicación Spring Boot que se encargan de detectar secuencias de ADN mutantes y calcular estadísticas de las secuencias registradas. A continuación te detallo cómo podrías implementar estas API.

1. API REST para detectar mutantes
   Esta API recibe una secuencia de ADN a través de un POST y determina si es mutante o no. La lógica de negocio la manejarás en el servicio MutantService. La respuesta varía dependiendo del resultado y si la secuencia de ADN ya ha sido registrada anteriormente.

* Controlador
>```java
>@RestController
>@RequiredArgsConstructor
>@RequestMapping("api/v1")
>@Tag(name = "Servicio para mutantes")
>public class MutantController {
>
>    private final MutantService mutantService;
>
>    @Operation(summary = "Nos permite validar si una cadena de ADN es mutante o es humana")
>    @ApiResponses(value = {
>            @ApiResponse(responseCode = "200", description = "El ADN entregado es un Mutante",
>                    content = @Content(mediaType = "application/json",
>                            schema = @Schema(implementation = ApiResponseUtil.class))),
>            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
>                    content = @Content(mediaType = "application/json")),
>            @ApiResponse(responseCode = "403", description = "El ADN entregado es un Humano",
>                    content = @Content(mediaType = "application/json")),
>            @ApiResponse(responseCode = "404", description = "El ADN entregado ya fue verificado",
>                    content = @Content(mediaType = "application/json"))
>    })
>    @PostMapping("/mutant")
>    public ResponseEntity<ApiResponseUtil<Object>> isMutant (@RequestBody @Valid  MutantDto mutantDto) {
>        return new ResponseEntity<ApiResponseUtil<Object>>(mutantService.isMutant(mutantDto), HttpStatus.OK);
>    }
>}
>```

* Servicio

Dentro del servicio MutantService, implementarás la lógica para determinar si una secuencia de ADN es mutante o no. También verificarás si la secuencia ya ha sido registrada antes, para retornar la respuesta adecuada.

>```java
>@Service
>@RequiredArgsConstructor
>public class MutantServiceImpl implements MutantService {
>
>    private static final Logger logger = LoggerFactory.getLogger(MutantServiceImpl.class);
>
>    private final MutantRepository mutantRepository;
>
>    private  final MutantUtil mutantUtil;
>    private final Util util;
>
>    @Override
>    @CacheEvict(value = "mutantStats", allEntries = true)
>    public ApiResponseUtil<Object> isMutant (MutantDto mutantDto) {
>
>        String dnaJson = util.arrayConverToJson(mutantDto.getDna());
>        Optional<MutantEntity> mutantOpt = mutantRepository.findByDnaSequence(dnaJson);
>        if (mutantOpt.isPresent()) {
>            throw new NotFoundException("This DNA sequence has already been used");
>        }
>
>        Boolean isMutant = mutantUtil.isMutantValid(mutantDto.getDna());
>
>        mutantRepository.save(MutantEntity
>                .builder()
>                .dnaSequence(dnaJson)
>                .isMutant(isMutant)
>                .build());
>
>        if (!isMutant) {
>            throw  new ForbiddenException("This sequence is not a mutant");
>        }
>
>        return util.mapaRespuesta(MutantDto.builder().dna(util.jsonConverToArray(dnaJson)).build());
>    }
>}
>```

* Respuestas Esperadas
1. ADN Mutante:
>```json
>{
> "message": "This sequence is a mutant",    
> "status": 200,
> "data": {
>     "dna": [
>         "ATGCGA",
>         "CAGTGC",
>         "TTATGT",
>         "AGAAGG",
>         "CCCCTA",
>         "TCACTG"
>     ]
>},
> "fieldErrors": []
>}
>```

2. ADN No Mutante:
>```json
>{
> "message": "This sequence is not a mutant",
> "status": 403,
> "data": [],
> "fieldErrors": []
>}
>```

3. ADN Ya Registrado:
>```json
>{
> "message": "This DNA sequence has already been used",
> "status": 404,
> "data": [],
> "fieldErrors": []
>}
>```

2. API REST para estadísticas

Esta API REST se encarga de consultar el número de secuencias mutantes y no mutantes que han sido registradas, y calcular la relación entre ambos.

* Controlador
>```java
>@RestController
>@RequiredArgsConstructor
>@RequestMapping("api/v1")
>@Tag(name = "Servicio para mutantes")
>public class MutantController {
>
>    private final MutantService mutantService;
>
>    @Operation(summary = "Nos permite realizar el cálculo para obtener las estadísticas de todas las cadenas de ADN")
>    @ApiResponses(value = {
>            @ApiResponse(responseCode = "200", description = "El ADN entregado es un Mutante",
>                    content = @Content(mediaType = "application/json",
>                            schema = @Schema(implementation = ApiResponseUtil.class))),
>            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
>                    content = @Content(mediaType = "application/json")),
>            @ApiResponse(responseCode = "403", description = "El ADN entregado es un Humano",
>                    content = @Content(mediaType = "application/json")),
>            @ApiResponse(responseCode = "404", description = "El ADN entregado ya fue verificado",
>                    content = @Content(mediaType = "application/json"))
>    })
>    @GetMapping("/status")
>    public ResponseEntity<ApiResponseUtil<Object>> mutantStatus() {
>        return new ResponseEntity<>(mutantService.isMutantCalculate(), HttpStatus.OK);
>    }
>}
>```

* Servicio 

En el servicio MutantService, implementarás la lógica para obtener el número de mutantes y no mutantes, y calcular el promedio.
>```java
>@Service
>@RequiredArgsConstructor
>public class MutantServiceImpl implements MutantService {
>
>    private static final Logger logger = LoggerFactory.getLogger(MutantServiceImpl.class);
>
>    private final MutantRepository mutantRepository;
>
>    private  final MutantUtil mutantUtil;
>    private final Util util;
>
>    @Override
>    @Cacheable("mutantStats")
>    public ApiResponseUtil<Object> isMutantCalculate () {
>
>        logger.info("isMutant method called, cache is being used");
>        Optional<IsMutantCalculate> isMutantCalculateOpt = mutantRepository.getMutantCalculate();
>        if (isMutantCalculateOpt.isEmpty()) {
>            throw new NotFoundException("There is no DNA to perform the calculations");
>        }
>
>        return util.mapaRespuesta(util.convertTo(isMutantCalculateOpt.get(), IsMutantDto.class));
>    }
>}
>```

* Respuesta Esperada
>```json
>{
> "message": "This sequence is a mutant",
> "status": 200,
> "data": {
>   "countMutantDna": 1,
>   "countHumanDna": 0,
>   "ratio": 0.0
> },
> "fieldErrors": []
>}
>```
