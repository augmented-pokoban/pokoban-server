import {Routes} from "@angular/router";
import {PokobanGuard} from "./guards/PokobanGuard";
import {PokobanGamesGuard} from "./guards/PokobanGamesGuard";
import {GameComponent} from "./components/game/game.component";
import {GamesComponent} from "./components/games/games.component";
import {LevelsComponent} from "./components/levels/levels.component";
import {LevelsGuard} from "./guards/LevelsGuard";
import {PokobanStateGuard} from "./guards/PokobanStateGuard";
import {ReplaysComponent} from "./components/replays/replays.component";
import {PokobanReplaysGuard} from "./guards/PokobanReplaysGuard";

export const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'games',
    pathMatch: 'full'
  },
  {
    path: 'levels',
    component: LevelsComponent,
    resolve: {
      levels: LevelsGuard
    },
  },
  {
    path: 'games',
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: GamesComponent,
        resolve: {
          pokobans: PokobanGamesGuard
        },
      },
      {
        path: ':id',
        component: GameComponent,
        resolve: {
          pokoban: PokobanGuard
        }
      },
      {
        path: 'state/:file',
        component: GameComponent,
        resolve: {
          pokoban: PokobanStateGuard
        }
      }
    ]
  },
  {
    path: 'replays',
    pathMatch: 'full',
    component: ReplaysComponent,
    resolve: {
      pokobans: PokobanReplaysGuard
    }

  }
];
