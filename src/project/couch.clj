(ns project.couch
  (:use couchdb.client))

(def *db* "http://localhost:5984")
(def *project* "project")

(defn
  #^{:doc "Create the database if it does not exists already."}
  create-database-if-needed []
  (database-create *db* *project*))

(defn
  #^{:doc "Save an object to the database."}
  save [obj]
  (document-create *db* *project* obj))

(defn
  #^{:doc "List all projects."}
  list-all-projects []
  (map #(:value %)
       (:rows (view-temp-get *db* *project*
                             {:map "function(doc) {
                                      if (doc.type == 'project')
                                        emit(null, doc)
                                      }"}))))

(defn
  #^{:doc "Load a specific project by the name and the version."}
  project-by-name-and-version [name version]
  (:value
   (:rows
    (view-temp-get *db* *project*
                   {:map
                    (str "function(doc) { if (doc.type == 'project' && ")}))))
