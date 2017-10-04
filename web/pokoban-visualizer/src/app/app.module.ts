import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {VisualizerComponent} from "./components/visualizer/visualizer.component";

@NgModule({
    declarations: [
        AppComponent,
        VisualizerComponent
    ],
    imports: [
        BrowserModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
