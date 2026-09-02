# QuickBite 🍽️

Une app Android (Jetpack Compose) pour décider rapidement quoi manger.

## USP
**"Tinder pour la bouffe" avec anti-répétition intelligente.**
Tu swipes des cartes de plats à droite (je choisis) ou à gauche (je passe).
L'app retient ton historique et **ne repropose pas un plat déjà choisi dans
les 3 derniers jours**, pour éviter de manger 3x la même chose sans t'en
rendre compte. Un écran Stats montre tes habitudes alimentaires.

## Architecture (codebase propre)
```
data/        -> Meal, MealCatalog, MealChoice (Room entity), Dao, Database, Repository
utils/       -> CooldownUtils (logique anti-répétition), DateUtils
viewmodel/   -> MealViewModel (StateFlow, expose l'état à l'UI)
navigation/  -> Screen, NavGraph
ui/theme/    -> Color, Theme, Type (Material 3)
ui/components/ -> SwipeableMealCard, HistoryRow, StatBar, BottomNavBar (réutilisables)
ui/screens/  -> SwipeScreen, HistoryScreen, StatsScreen
```
Persistance locale avec Room, état géré via ViewModel + StateFlow,
navigation avec navigation-compose, tout en Material 3.

---

## 1. Ouvrir et builder le projet

1. Ouvre **Android Studio** (version récente, ex. Koala/Ladybug).
2. `File > Open` → sélectionne le dossier `QuickBite` (celui contenant `settings.gradle.kts`).
3. Laisse Gradle se synchroniser (première fois = quelques minutes).
4. Lance l'app sur un émulateur ou un téléphone (`Run ▶`).

Si Gradle demande de mettre à jour le plugin Android / Kotlin, accepte
les versions proposées automatiquement, ça reste compatible.

## 2. Générer l'APK

Dans Android Studio :
`Build > Build Bundle(s) / APK(s) > Build APK(s)`

L'APK se trouve ensuite dans :
`app/build/outputs/apk/debug/app-debug.apk`

C'est cet APK qu'il faut ajouter au repo GitHub (voir étape 3).

## 3. Créer le repo GitHub et soumettre

```bash
cd QuickBite
git init
git add .
git commit -m "QuickBite - herexamen"
git branch -M main
git remote add origin https://github.com/TON_USERNAME/QuickBite.git
git push -u origin main
```

Puis ajoute l'APK au repo :
```bash
mkdir -p apk
cp app/build/outputs/apk/debug/app-debug.apk apk/QuickBite.apk
git add apk/QuickBite.apk
git commit -m "Add built APK"
git push
```

Ensuite sur GitHub :
- Rends le repo **public**, OU
- Va dans `Settings > Collaborators` et invite **JanssensJochemEHB**.

Soumets l'URL du repo avant **mercredi 23:59**.

## 4. Présentation (max 10 min)

Points à couvrir :
- **Overview** : QuickBite = décider quoi manger via swipe, comme Tinder.
- **Fonctionnalités principales** :
  - Swipe cards avec geste de drag personnalisé (pas de librairie externe)
  - Anti-répétition automatique (cooldown de 3 jours) — c'est le cœur de l'USP
  - Historique persistant (Room database)
  - Stats des plats les plus choisis
  - Navigation par bottom bar entre 3 écrans
- **Plus gros défi rencontré** : implémenter le geste de swipe personnalisé
  avec `pointerInput` + `detectDragGestures` + `Animatable` pour l'offset,
  et synchroniser ça avec un état Room réactif (Flow → StateFlow) pour que
  la liste de cartes se mette à jour automatiquement sans bug de recomposition.

Pas besoin de slides — tu peux directement montrer l'app en live + le code.
