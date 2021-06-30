// ************************************************************************************************
// Vraag 5 van 5 (50 van 50 RxAvanspunten totaal)
//
// Deze vraag bestaat uit drie delen
//
// We hebben tijdens de lessen RxAvans van een objectgeoriënteerde implementatie volledig 
// omgeschreven naar een oplossing in het functionele paradigma. We kennen nu vier varianten van 
// reactive links: fork, map, filter en log. Voor deze vraag maak je twee links erbij, de buffer
// (in deel a) en de debuffer (in deel b). In deel c koppel je de gemaakte links in de bestaande 
// reactive chain.
// 
// Let op: de uitwerking van deze vraag maak je in de bestanden Main.scala en RxAvans.scala!
// ************************************************************************************************

// ================================================================================================
// Deel a (20 RxAvanspunten)
//
// Schrijf de functie buffer in het bestand RxAvans.scala op de daarvoor aangegeven plek. Deze 
// functie heeft de signature (Observable[A], Int) => Observable[List[A]] en krijgt dus een 
// buffergrootte als tweede argument. De functie die uitgevoerd wordt als een observer gegeven 
// wordt, spaart de data die doorgegeven wordt op tot de grootte van de buffer bereikt is en stuurt 
// dan de gehele buffer als list door naar de observer.
//
// Hint: je mag hier gebruik maken van een mutable variabele!
// ================================================================================================

// ================================================================================================
// Deel b (20 RxAvanspunten)
//
// Schrijf de functie debuffer in het bestand RxAvans.scala op de daarvoor aangegeven plek. Deze 
// functie heeft de signature Observable[List[A]] => Observable[A]. De functie die uitgevoerd wordt 
// als een observer gegeven wordt, stuurt de data uit de gegeven lijst een voor een door naar de 
// observer.
// ================================================================================================

// ================================================================================================
// Deel c (10 RxAvanspunten)
//
// Toon de werking van je geschreven functies aan door ze toe te voegen aan de reactive chain in 
// Main.scala, op de plek waar dit aangegeven staat. De buffer wordt in de chain vóór de debuffer 
// geplaatst. De debuffer wordt dus een observer van de buffer. De grootte van de buffer maakt voor
// het aantonen van de werking niet uit. Het gaat hier om een uitbreiding van de chain die niet 
// per se zinnig is, maar vooral bedoeld is om de werking te tonen.
// ================================================================================================