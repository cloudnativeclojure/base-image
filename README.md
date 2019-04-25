# base-image

This is seriously POC.

Don't even consider using it at this point.


(require '[example.server :as srv])
(srv/start)

(slurp "http://localhost:3000/plain/plus?x=10&y=20")
