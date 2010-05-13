(ns project.mongo
  (:use somnium.congomongo))

(mongo!
 :db "project")

(defn
  #^{:doc "Save an object to the database."}
  save [obj]
  (insert! (:type obj) obj))

(defn
  #^{:doc "Update an object to the database."}
  update [old obj]
  (update! (:type obj) old obj))

(defn
  #^{:doc "Find all projects"}
  list-all-projects []
  (fetch :project))

(defn
  #^{:doc "Find all questionnaire-templates"}
  list-all-questionnaire-templates []
  (fetch :questionnaire-template))

(defn
  #^{:doc "Find a project by name and version."}
  project-by-name-and-version [name version]
  (fetch-one
   :project
   :where {:name name
           :version version}))

(defn
  #^{:doc "Find a questionnaire template by shortname and version."}
  questionnaire-template-by-shortname-and-version [shortname version]
  (fetch-one
   :questionnaire-template
   :where {:shortname shortname
           :version version}))
