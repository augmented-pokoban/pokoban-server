import {Routes} from "@angular/router";
import {VisualizerComponent} from "./components/visualizer/visualizer.component";
import {PokobanGuard} from "./guards/PokobanGuard";

export const appRoutes: Routes = [
    {
        path: 'visualize/:id',
        component: VisualizerComponent,
        resolve: {
            pokoban: PokobanGuard
        }
    },
];