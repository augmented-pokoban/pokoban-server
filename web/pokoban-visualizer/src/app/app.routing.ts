import {Routes} from "@angular/router";
import {PokobanGuard} from "./guards/PokobanGuard";
import {PokobansGuard} from "./guards/PokobansGuard";
import {GameComponent} from "./components/game/game.component";
import {GamesComponent} from "./components/games/games.component";
import {LevelsComponent} from "./components/levels/levels.component";
import {LevelsGuard} from "./guards/LevelsGuard";

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
                    pokobans: PokobansGuard
                },
            },
            {
                path: ':id',
                component: GameComponent,
                resolve: {
                    pokoban: PokobanGuard
                }
            }
        ]
    },
];
