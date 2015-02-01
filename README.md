# NotificationListener

It is a android phone notification listener that can listen to the notifications on your phone and show it in a Firefox add-on.

NotificationListener:
The folder contains the mobile side of the work. If you install it and give it Notification access, it will listen to the notifications and push that to a Rest server.

wood:
This is a Restful server (I wrote before). I added a API in it so it can allow the android side push the notification to a queue.

nl.xpi
This is a Firefox add-on that can query the API for the notidication once you click the botton and show that on your screen.

Inspired by Chengyi Zhou and Zikang Zhao
