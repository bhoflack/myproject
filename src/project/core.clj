(ns project.core
  (:use project.model
        project.mongo
        project.page
        compojure.core
        ring.adapter.jetty))

(defroutes project-routes
  (GET "/project" [] (projects-page (list-all-projects)))
  (GET "/project/:name/:version" [name version]
       (project-detail-page
        (project-by-name-and-version name version)))
  (GET "/questionnaire" [] (questionnaire-templates-page (list-all-questionnaire-templates)))
  (GET "/questionnaire/:shortname/:version" [shortname version]
       (questionnaire-template-detail-page
        (questionnaire-template-by-shortname-and-version shortname (Integer/parseInt version)))))

(defn start []
  (run-jetty project-routes {:port 8080}))


