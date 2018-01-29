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
import {RunningComponent} from "./components/running/running.component";
import {PokobanRunningGuard} from "./guards/PokobanRunningGuard";
import {LevelComponent} from "./components/level/level.component";
import {LevelGuard} from "./guards/LevelGuard";
import {EncodingComponent} from "./components/encoding/encoding.component";
import {EncodingGuard} from "./guards/EncodingGuard";
import {EncodingVisualizerComponent} from "./components/encoding-visualizer/encoding-visualizer.component";

export const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'games',
    pathMatch: 'full'
  },
  {
    path: 'levels',
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: LevelsComponent,
        resolve: {
          paginationResp: LevelsGuard
        },
      },
      {
        path: ':level',
        component: LevelComponent,
        resolve: {
          level: LevelGuard
        }
      },
    ]
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
        path: 'state/:folder/:file',
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
  },
  {
    path: 'running',
    pathMatch: 'full',
    component: RunningComponent,
    resolve: {
      running: PokobanRunningGuard
    }
  },
  {
    path: 'encoding',
    children: [
      {
        path: '',
        pathMatch: 'full',
        component: EncodingComponent,
        resolve: {
          encodings: EncodingGuard
        },
      },
      {
        path: 'visualizer',
        pathMatch: 'full',
        component: EncodingVisualizerComponent
      }
    ]
  }

];
