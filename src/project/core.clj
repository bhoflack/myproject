(ns project.core
  (:use project.model
        project.mongo
        project.page
        compojure.core
        compojure.route
        ring.adapter.jetty))

(defn handle-update-positions [items positions]
  (map #(nth items (Integer/parseInt %)) positions))

(defn sort-questions [qt positions]
  (assoc qt :questions (handle-update-positions (:questions qt) positions)))

(defroutes project-routes
  (GET "/project" [] (projects-page (list-all-projects)))
  (GET "/project/:name/:version" [name version]
       (project-detail-page
        (project-by-name-and-version name version)))
  (GET "/questionnaire" [] (questionnaire-templates-page (list-all-questionnaire-templates)))
  (GET "/questionnaire/:shortname/:version/" [shortname version]
       (questionnaire-template-detail-page
        (questionnaire-template-by-shortname-and-version
         shortname
         (Integer/parseInt version))))
  (GET "/questionnaire/update_positions"
       {params :params}
	(let [shortname (get params "shortname")
	      version (Integer/parseInt (get params "version"))
	      order (get params "items[]")
              old (questionnaire-template-by-shortname-and-version shortname version)]
	(update old (sort-questions old order))))
  (files "/media" {:root "public"}))

(defn start []
  (run-jetty project-routes {:port 8080}))


