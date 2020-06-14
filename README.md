# TestApp


I perhaps made the mistake of being overly ambitious as I decided to use coroutines and jetpack's lifecycle libraries (MVVM) for this task. I'm more used to working with MVP and using RxJava to handle asynchronous work, but I know coroutines are more efficient. Due to my unfamiliarity with these libraries the task took me a little longer than expected and I was not able to finish everything. All the mandatory work and half the bonus (use mvvm/mvp and caching) are complete. 

**Difficulties:**
- I initially had some trouble wrapping my head around the API. The forecast api presented the biggest challenge as there isn't a clear way to get the day's high/low, so I made use of some helper functions (or perhaps I'm missing something?). I also tried my best to avoid quadratic time complexity for said functions. The symbol api also presented a challenge as there isn't a single JSON property to match a specific icon (for night/day/polar styled icons), so I created some helper functions there as well. 
- Getting to grips with coroutines and how to setup LiveData and ViewModels also took some time.

**Future improvements:**
- Better error handling/show errors to user
- Better UI styling
- Optimize helper functions
- Some light refactoring

**Notes:**
- The first screen is blank as I had intended to show the user's current location as soon as the app as started, atm it's empty with only the menu bar. 
- Overall I maybe should have used the libraries I'm more familiar with as I would've saved some time. Had I had another day or two to work on this, I would've liked to attempt the other bonus tasks.
- Some locations within shortIntervals only show weather for up to 3 days?
