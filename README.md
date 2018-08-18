# AsyncTaskLoader
AsyncTask and Loaders

- Loaders provide a framework to perform asynchronous loading of a data.

- AsyncTaskLoader is a better choice for activity_bound_thread managment, bcoz it handles lifecycle changes correctly, delivering the results to the current active activity, preventing
  duplication of background threads.
