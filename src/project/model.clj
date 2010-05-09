(ns project.model)

(defn
  #^{:doc "Create a new project."
     :test (fn []
             (assert (= {:type :project :name "66666" :version "AA"}
                        (project {:name "66666" :version "AA"}))))}
  project [{:keys [name version customer nickname]}]
  {:type :project
   :name name
   :version version
   :customer customer
   :nickname nickname })

(defn
  #^{:doc "Create a new questionnaire template."
     :test (fn []
             (assert (= {:type :questionnaire-template
                         :name "Kick off"
                         :shortname "KO"
                         :version 1
                         :questions [{:question "Finished?"
                                      :responsible "pm"}
                                     {:question "Everything ok?"
                                      :responsible "sa"}]}
                        (questionnaire-template {:name "Kick off"
                                                 :shortname "KO"
                                                 :version 1
                                                 :questions
                                                 [{:question "Finished?"
                                                   :responsible "pm"}
                                                  {:question "Everything ok?"
                                                   :responsible "sa"}]}))))}
  questionnaire-template [{:keys [name shortname version questions]}]
  {:type :questionnaire-template
   :name name
   :shortname shortname
   :version version
   :questions questions})

(defn
  #^{:doc "Short description of a project."
     :test (fn []
             (assert (= "project '66666' version 'AA'"
                        (short-description (project "66666" "AA")))))}
  short-description [project]
  (str "project '" (:name project) "' version '" (:version project) "'"))
