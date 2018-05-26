# background_taks
Background Tasks

https://classroom.udacity.com/courses/ud851/lessons/f5ef4e52-c485-4c85-a26a-3231c17d6154/concepts/0fdcfb9d-31c2-44f7-996b-4119bcc269a6

### Loader vs Service

![](/images/loader_vs_service.png)

![](/images/start_service.png)

![](/images/service_lifecycle.png)

![](/images/intent_service.png)

### Intents vs PendingIntents

![](/images/intents.png)

![](/images/pedingintents.png)

### Foreground Service

![](/images/foreground_service.png)

### Application priority

![](/images/app_priority.png)

![](/images/three_laws.png)

### Scheduling Jobs

![](/images/scheduling_jobs.png)

### Broadcast Receivers

![](/images/broadcast_receivers.png)

![](/images/intent_filter.png)

![](/images/static_broadcast_receiver.png)

![](/images/static_dynamic_broadcast_receiver.png)

	To simulate the phone being unplugged from usb charging you can use:

	adb shell dumpsys battery set usb 0

	or if you're on a device Android 6.0 or higher you can use:

	adb shell dumpsys battery unplug

	To "plug" the phone back in, just reset it's charging status using:

	adb shell dumpsys battery reset

