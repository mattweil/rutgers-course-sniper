<h1>Rutgers Course Sniper</h1>

Rutgers Course Sniper is a command line project written in Java that allows you to be notified when a particular section in a course becomes available.

<h2>How to use</h2>



| Command | Description | Usage | Optional Parameters
| --- | --- | --- | ---
| `snipesection` | Begins watching the provided section within a course | snipesection [sectionindex] | -a, -e
| `snipesections` | Begins watching the provided sections within a course | snipesections [sectionindex, sectionindex] | -a, -e
| `snipecourse` | Begins watching all sections within a provided course  | snipecourse [courseindex] | -p, -e
| `snipecourses` | Begins watching all sections within provided courses  | snipecourses [courseindex, courseindex] | -e
| `web` | Places sniper into web mode to be used on a server | web  | 
| `refresh` | Allows for the configuration of sniper's refresh rate | refresh [time in seconds] | 
| `help` | Displays a list of commands and their usage | help [command] | 
