# ToDo App
A simple ToDo App that develop with most modern technologies, such as:
- Kotlin
- Jetpack Compose
- Clean Architecture
- Hilt
- Coroutines
- Room
- Version Catalogue

## Project Structure
Globally the project has the following top level packages:
1. **data**: Basically data layer that contains data source(this case only have local data source), and all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Dagger-Hilt.
3. **ui**: Basically presenter layer that contains the Compose Components and ViewModel.
4. **domain**: Basically domain layer that contains all business logic.
5. **utils**: Contains Utility Class, Extension function, etc.
6. **navigation**: Contains navigation for the app.

## Features Explanation
1. Create New Tasks ⇒ you can simply click add button on bottom-end of your screen, then input all the data and save.
2. Show All Tasks ⇒ you will see all your tasks that you created at home screen
3. Mark Tasks Done ⇒ you can mark your task with simply click the circular bar on the item.
4. Delete Task ⇒ you can delete your task with **drag to the left**.
5. Filter Task by Date ⇒
6. Delete completed task ⇒ you can click three dots in the top bar. And choose Delete completed tasks.

<img src="https://github.com/dimasaryamurdiyan/todo-compose/blob/main/recording.gif" width="300" height="650">

## Credits
- **Dimas Arya Murdiyan** - dimasaryamurdiyan123@gmail.com
- [Dribble To Do App UI Design](https://dribbble.com/shots/14100356-ToDo-App-UI)