# Commit-Richtlinie für Java-Projekte

## Ziel 
Kosmetische, technische und funktionale Änderungen sollen getrennt bleiben, um Code Reviews, Cherry-Picking und Release-Management zu erleichtern.

## Aufbau einer Commit-Message

	<type>(optional scope): <kurze beschreibung>

	[optional: detailliertere beschreibung im body]

## Commit-Typen

	feat – neues Feature oder funktionale Erweiterung  
	fix – Fehlerbehebung, Bugfix  
	refactor – Code-Umstrukturierung ohne Verhaltensänderung  
	style – Formatierungen, Imports, Whitespace, Kommentare, keine Logikänderung  
	docs – Dokumentation (README, Javadoc, Kommentare)  
	test – Tests hinzugefügt, angepasst oder verbessert  
	chore – technische Änderungen (Build, CI, Dependencies, Config)  
	perf – Performance-Optimierungen   

## Beispiele für gute Commit Messages

feat(order): add validation for order quantity  
fix(model): prevent NPE on invalid input  
refactor: extract duplicate parsing logic into helper method  
style: clean up imports and fix indentation in UserService  
docs(shared): add usage example for Loggable
test: add integration tests for user login
chore: update Maven dependencies
perf: cache parsed JSON in ConfigLoader

## Umgang mit beiläufigen Änderungen (style, docs, refactor)

1. Stage diese separat mit git add -p  
2. Committe sie zuerst als eigenen style- oder refactor-Commit  
3. Danach den eigentlichen Feature- oder Fix-Commit

## Checkliste vor dem Commit

- Betrifft der Commit nur eine logische Änderungseinheit?  
- Ist klar, ob Verhalten geändert wird oder nicht?  
- Ist die Message prägnant und im Imperativ formuliert (add statt added)?  
- Kosmetische Änderungen getrennt von Feature-/Bugfix-Commits?
