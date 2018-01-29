import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {appRoutes} from "./app.routing";
import {PokobanService} from "./services/PokobanService";
import {PokobanGuard} from "./guards/PokobanGuard";
import {HttpModule} from "@angular/http";
import {HomeComponent} from "./components/home/home.component";
import {PokobanGamesGuard} from "./guards/PokobanGamesGuard";
import {GameComponent} from "./components/game/game.component";
import {GamesComponent} from "./components/games/games.component";
import {LevelsComponent} from "./components/levels/levels.component";
import {LevelsGuard} from "./guards/LevelsGuard";
import {LevelService} from "./services/LevelService";
import {FormsModule} from "@angular/forms";
import {PokobanStateGuard} from './guards/PokobanStateGuard';
import {PokobanReplaysGuard} from './guards/PokobanReplaysGuard';
import {ReplaysComponent} from "./components/replays/replays.component";
import {NgxPaginationModule} from "ngx-pagination";
import {PokobanRunningGuard} from "./guards/PokobanRunningGuard";
import {RunningComponent} from "./components/running/running.component";
import {LevelComponent} from "./components/level/level.component";
import {LevelGuard} from "./guards/LevelGuard";
import {VisualizerComponent} from "./components/visualizer/visualizer.component";

const GUARDS = [
    PokobanGuard,
    PokobanGamesGuard,
    PokobanReplaysGuard,
    LevelsGuard,
    LevelGuard,
    PokobanStateGuard,
    PokobanRunningGuard
];

const SERVICES = [
    PokobanService,
    LevelService,
];

const COMPONENTS = [
    AppComponent,
    HomeComponent,
    GameComponent,
    GamesComponent,
    LevelsComponent,
    LevelComponent,
    ReplaysComponent,
    RunningComponent,
    VisualizerComponent
];

@NgModule({
    declarations: [
        ...COMPONENTS
    ],
    imports: [
        BrowserModule,
        HttpModule,
        RouterModule.forRoot(appRoutes, {enableTracing: false}),
        FormsModule,
        NgxPaginationModule
    ],
    providers: [
        ...SERVICES,
        ...GUARDS
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
