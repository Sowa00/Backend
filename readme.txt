Informacje o projekcie:
Praca dyplomowa inżynierska,
Temat pracy: Portal internetowy do obsługi uczelnianej stacji pogodowej
Imię i nazwisko autora: Jakub Sowa, student Akademii Górniczo-Hutniczej

Instrukcja uruchomienia aplikacji lokalnie:

1. Aby uruchomić aplikację lokalnie należy skonfigurować VPN aby mieć dostęp do uczelnianej bazy danych.
2. Następnie trzeba uruchomić backend (serwer). Można zrobić to za pomocą skompilowania kodu źródłowego serwera (wersja Javy 17) lub uruchomienie zbudowanej wersji projektu (mozna zbudować za pomocą Maven).
3. Kolejno należy uruchomić frontend (interfejs użytkownika). Można zrobić to za pomocą skompilowania kodu źródłowego serwera (Angular) lub poprzez uruchomienie zbudowanej wersji projektu (można zbudować za pomocą polecenia "ng build" w terminalu).
4. Gdy wszystkie kroki zostały spełnione strona internetowa powinna być dostępna lokalnie pod adresem localhost:<port>.