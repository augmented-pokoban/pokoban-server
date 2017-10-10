import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RouterModule} from "@angular/router";
import {appRoutes} from "./app.routing";
import {PokobanService} from "./services/PokobanService";
import {PokobanGuard} from "./guards/PokobanGuard";
import {HttpModule} from "@angular/http";
import {HomeComponent} from "./components/home/home.component";
import {PokobansGuard} from "./guards/PokobansGuard";
import {GameComponent} from "./components/game/game.component";
import {GamesComponent} from "./components/games/games.component";
import {LevelsComponent} from "./components/levels/levels.component";
import {LevelsGuard} from "./guards/LevelsGuard";
import {LevelService} from "./services/LevelService";
import {FormsModule} from "@angular/forms";
import {PokobanStateGuard} from './guards/PokobanStateGuard';

const GUARDS = [
    PokobanGuard,
    PokobansGuard,
    LevelsGuard,
    PokobanStateGuard
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
];

@NgModule({
    declarations: [
        ...COMPONENTS
    ],
    imports: [
        BrowserModule,
        HttpModule,
        RouterModule.forRoot(appRoutes, {enableTracing: false}),
        FormsModule
    ],
    providers: [
        ...SERVICES,
        ...GUARDS
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
