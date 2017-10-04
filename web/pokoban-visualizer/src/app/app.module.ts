import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {VisualizerComponent} from "./components/visualizer/visualizer.component";
import {RouterModule} from "@angular/router";
import {appRoutes} from "./app.routing";
import {PokobanService} from "./services/PokobanService";
import {PokobanGuard} from "./guards/PokobanGuard";
import {HttpModule} from "@angular/http";

const GUARDS = [
    PokobanGuard
];

const SERVICES = [
    PokobanService
];

const COMPONENTS = [
    AppComponent,
    VisualizerComponent,
];

@NgModule({
    declarations: [
        ...COMPONENTS
]   ,
    imports: [
        BrowserModule,
        HttpModule,
        RouterModule.forRoot(appRoutes, {enableTracing: false})
    ],
    providers: [
        ...SERVICES,
        ...GUARDS
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
