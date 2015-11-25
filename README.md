# DØK '14 Snake
### Sådan håndterer vi branches
**Master Branch**

Her ligger vi den færdige kode, som er gennemtestet og vi er sikre på virker. Det er kun Henrik som kan merge en branch ind i master.

**Develop branch**

Kode som er blevet testet af test-gruppen, og som vi kan arbejde videre på, til nye funktioner i programmet

**Jeres branches**

I jeres gruppe, opretter i en branch med jeres gruppenavn og comitter jeres kode her til. Når I er tilfredse med resultatet af jeres kode, kan I give det til test-gruppen som gennemtester funktionaliteten.

###Git Cheatsheet

Hvis I vil lege med git i en interaktiv øvelse, prøv da [denne guide](https://try.github.io/levels/1/challenges/1)

- Skift branch: `git checkout branch-name`
  - Ex: `git checkout develop`
- Hent opdateringer fra repo: `git pull`
- Hvad er status/ændret: `git status`
- Hvad er ændringerne: `git diff`
- Tilføj dine ændringer til dit commit: `git add file.java`
  - Obs ønsker du at tilføje alle dine ændringer `git add .`
- Commit dine ændringer: `git commit -m "din commit besked"`
- Skub dine ændringer til repo: `git push`

**Generelt git workflow**

Sørg for at `commit` ofte. Pas på med ikke at have for mange ventende `commit` da det giver større sandsynlighed for merge conflits for andre. 

`push` så snart du har kode som virker, og `commit/push` aldrig når du har compiler/syntax fejl.

Inden at du lavet et `push`, sørg for at lave en `pull`så du er sikker på, at du har sidst opdateret kode.
