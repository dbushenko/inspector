(ns inspector.core)

(defn inspect-full
  "Inspects all public members of the object (detailed format)"
  [obj]
  (let [cl (.getClass obj)
        cs (map #(.toString %) (seq (.getConstructors cl)))
        ms (map #(.toString %) (seq (.getMethods cl)))
        fs (map #(.toString %) (seq (.getFields cl)))]
    (println "Class:" cl)
    (println "Constructors:")
    (dorun (map println cs))
    (println "Methods:")
    (dorun (map println ms))
    (println "Fields:")
    (dorun (map println fs))))

(defn inspect
  "Inspects all public members of the object (brief format)"
  [obj]
  (let [cl (.getClass obj)
        cls (-> cl
                str
                (.replace "class " ""))
        cs (map #(.toString %) (seq (.getConstructors cl)))
        ms (map #(-> %
                     .toString
                     (.replace (str "public static " cls) "public static")
                     (.replace (str "public " cls) "public")
                     (.replace (str cls ".") ""))
                (seq (.getMethods cl)))
        fs (map #(.toString %) (seq (.getFields cl)))]
    (println "Class:" cl)
    (println "Constructors:")
    (dorun (map println cs))
    (println "Methods:")
    (dorun (map println ms))
    (println "Fields:")
    (dorun (map println fs))))
