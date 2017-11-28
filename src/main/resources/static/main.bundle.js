webpackJsonp(["main"],{

/***/ "../../../../../src/$$_gendir lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_gendir lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <nav class=\"navbar navbar-default\">\n    <div class=\"container-fluid\">\n      <div class=\"navbar-header\">\n        <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\"\n                data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">\n          <span class=\"sr-only\">Toggle navigation</span>\n          <span class=\"icon-bar\"></span>\n          <span class=\"icon-bar\"></span>\n          <span class=\"icon-bar\"></span>\n        </button>\n        <a class=\"navbar-brand\" href=\"#\">Pokoban</a>\n      </div>\n      <ul class=\"nav navbar-nav\">\n        <li><a href=\"levels\">Levels</a></li>\n        <li><a href=\"games\">Expert moves</a></li>\n        <li><a href=\"replays\">Replays</a></li>\n      </ul>\n    </div>\n  </nav>\n  <router-outlet></router-outlet>\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = (function () {
    function AppComponent() {
    }
    return AppComponent;
}());
AppComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["o" /* Component */])({
        selector: 'app-root',
        template: __webpack_require__("../../../../../src/app/app.component.html"),
        styleUrls: []
    })
], AppComponent);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/@angular/platform-browser.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_routing__ = __webpack_require__("../../../../../src/app/app.routing.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_PokobanService__ = __webpack_require__("../../../../../src/app/services/PokobanService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__guards_PokobanGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__components_home_home_component__ = __webpack_require__("../../../../../src/app/components/home/home.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__guards_PokobanGamesGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanGamesGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__components_game_game_component__ = __webpack_require__("../../../../../src/app/components/game/game.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__components_games_games_component__ = __webpack_require__("../../../../../src/app/components/games/games.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__components_levels_levels_component__ = __webpack_require__("../../../../../src/app/components/levels/levels.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__guards_LevelsGuard__ = __webpack_require__("../../../../../src/app/guards/LevelsGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__services_LevelService__ = __webpack_require__("../../../../../src/app/services/LevelService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__guards_PokobanStateGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanStateGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__guards_PokobanReplaysGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanReplaysGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__components_replays_replays_component__ = __webpack_require__("../../../../../src/app/components/replays/replays.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19_ngx_pagination__ = __webpack_require__("../../../../ngx-pagination/dist/ngx-pagination.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};




















var GUARDS = [
    __WEBPACK_IMPORTED_MODULE_6__guards_PokobanGuard__["a" /* PokobanGuard */],
    __WEBPACK_IMPORTED_MODULE_9__guards_PokobanGamesGuard__["a" /* PokobanGamesGuard */],
    __WEBPACK_IMPORTED_MODULE_17__guards_PokobanReplaysGuard__["a" /* PokobanReplaysGuard */],
    __WEBPACK_IMPORTED_MODULE_13__guards_LevelsGuard__["a" /* LevelsGuard */],
    __WEBPACK_IMPORTED_MODULE_16__guards_PokobanStateGuard__["a" /* PokobanStateGuard */]
];
var SERVICES = [
    __WEBPACK_IMPORTED_MODULE_5__services_PokobanService__["a" /* PokobanService */],
    __WEBPACK_IMPORTED_MODULE_14__services_LevelService__["a" /* LevelService */],
];
var COMPONENTS = [
    __WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* AppComponent */],
    __WEBPACK_IMPORTED_MODULE_8__components_home_home_component__["a" /* HomeComponent */],
    __WEBPACK_IMPORTED_MODULE_10__components_game_game_component__["a" /* GameComponent */],
    __WEBPACK_IMPORTED_MODULE_11__components_games_games_component__["a" /* GamesComponent */],
    __WEBPACK_IMPORTED_MODULE_12__components_levels_levels_component__["a" /* LevelsComponent */],
    __WEBPACK_IMPORTED_MODULE_18__components_replays_replays_component__["a" /* ReplaysComponent */]
];
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["M" /* NgModule */])({
        declarations: COMPONENTS.slice(),
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_7__angular_http__["b" /* HttpModule */],
            __WEBPACK_IMPORTED_MODULE_3__angular_router__["b" /* RouterModule */].forRoot(__WEBPACK_IMPORTED_MODULE_4__app_routing__["a" /* appRoutes */], { enableTracing: false }),
            __WEBPACK_IMPORTED_MODULE_15__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_19_ngx_pagination__["a" /* NgxPaginationModule */]
        ],
        providers: SERVICES.concat(GUARDS),
        bootstrap: [__WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ "../../../../../src/app/app.routing.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return appRoutes; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__guards_PokobanGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__guards_PokobanGamesGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanGamesGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__components_game_game_component__ = __webpack_require__("../../../../../src/app/components/game/game.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__components_games_games_component__ = __webpack_require__("../../../../../src/app/components/games/games.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__components_levels_levels_component__ = __webpack_require__("../../../../../src/app/components/levels/levels.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__guards_LevelsGuard__ = __webpack_require__("../../../../../src/app/guards/LevelsGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__guards_PokobanStateGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanStateGuard.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__components_replays_replays_component__ = __webpack_require__("../../../../../src/app/components/replays/replays.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__guards_PokobanReplaysGuard__ = __webpack_require__("../../../../../src/app/guards/PokobanReplaysGuard.ts");









var appRoutes = [
    {
        path: '',
        redirectTo: 'games',
        pathMatch: 'full'
    },
    {
        path: 'levels',
        component: __WEBPACK_IMPORTED_MODULE_4__components_levels_levels_component__["a" /* LevelsComponent */],
        resolve: {
            paginationResp: __WEBPACK_IMPORTED_MODULE_5__guards_LevelsGuard__["a" /* LevelsGuard */]
        },
    },
    {
        path: 'games',
        children: [
            {
                path: '',
                pathMatch: 'full',
                component: __WEBPACK_IMPORTED_MODULE_3__components_games_games_component__["a" /* GamesComponent */],
                resolve: {
                    pokobans: __WEBPACK_IMPORTED_MODULE_1__guards_PokobanGamesGuard__["a" /* PokobanGamesGuard */]
                },
            },
            {
                path: ':id',
                component: __WEBPACK_IMPORTED_MODULE_2__components_game_game_component__["a" /* GameComponent */],
                resolve: {
                    pokoban: __WEBPACK_IMPORTED_MODULE_0__guards_PokobanGuard__["a" /* PokobanGuard */]
                }
            },
            {
                path: 'state/:file',
                component: __WEBPACK_IMPORTED_MODULE_2__components_game_game_component__["a" /* GameComponent */],
                resolve: {
                    pokoban: __WEBPACK_IMPORTED_MODULE_6__guards_PokobanStateGuard__["a" /* PokobanStateGuard */]
                }
            }
        ]
    },
    {
        path: 'replays',
        pathMatch: 'full',
        component: __WEBPACK_IMPORTED_MODULE_7__components_replays_replays_component__["a" /* ReplaysComponent */],
        resolve: {
            pokobans: __WEBPACK_IMPORTED_MODULE_8__guards_PokobanReplaysGuard__["a" /* PokobanReplaysGuard */]
        }
    }
];
//# sourceMappingURL=app.routing.js.map

/***/ }),

/***/ "../../../../../src/app/components/game/game.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "canvas {\n\n}\n\n.trans-selected {\n  background-color: lightgreen;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/game/game.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"col-md-8\">\n  <canvas #canvas height=\"750\" width=\"750\"></canvas>\n</div>\n<div class=\"col-md-4\">\n  <form class=\"form-horizontal\">\n    <div class=\"form-group\">\n      <label for=\"seconds\" class=\"col-md-2 control-label\">Speed</label>\n      <div class=\"col-md-4\">\n        <input type=\"text\" class=\"form-control\" id=\"seconds\" [(ngModel)]=\"msPerAction\"\n               [ngModelOptions]=\"{standalone: true}\"/>\n      </div>\n      <label for=\"play\" class=\"col-md-3 control-label\">Play <input type=\"checkbox\" id=\"play\"\n                                                                   [(ngModel)]=\"play\"\n                                                                   [ngModelOptions]=\"{standalone: true}\"\n                                                                   (ngModelChange)=\"playOnOff()\"/>\n      </label>\n    </div>\n  </form>\n\n\n  <div class=\"card\" style=\"overflow-y: auto; height: 700px\">\n    <ul class=\"list-group\">\n      <li class=\"list-group-item\" style=\"height: 40px\"\n          *ngFor=\"let transition of pokoban.transitions; let i = index\" [class.trans-selected]=\"i == (nextIndex-1)\"\n        (click)=\"selectState(i)\">\n        <span class=\"col-md-2\">{{i + 1 | number:'3.0-0'}}</span>\n        <span class=\"col-md-5\">{{transition.action}}</span>\n        <span class=\"col-md-5\">{{transition.success ? 'Success' : 'Failure'}}</span>\n      </li>\n    </ul>\n  </div>\n\n</div>\n"

/***/ }),

/***/ "../../../../../src/app/components/game/game.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GameComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__models_PokobanTransition__ = __webpack_require__("../../../../../src/app/models/PokobanTransition.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var GameComponent = (function () {
    function GameComponent(route) {
        this.route = route;
        this.msPerAction = 250;
        this.play = true;
        this.nextIndex = 1;
    }
    GameComponent.prototype.handleKeyboardEvent = function (event) {
        if (event.keyCode == 32) {
            this.play = !this.play;
            this.playOnOff();
        }
    };
    GameComponent.prototype.ngOnInit = function () {
        this.pokoban = this.route.snapshot.data['pokoban'];
    };
    GameComponent.prototype.ngAfterContentInit = function () {
        // state to draw
        var state = this.pokoban.initial;
        var canvas = this.canvas.nativeElement;
        this.baseWidth = canvas.width / state.dimensions;
        this.baseHeight = canvas.height / state.dimensions;
        this.ctx = canvas.getContext('2d');
        // draw initial state
        this.drawState(canvas, state);
        // Insert initial state into transition list as first element
        var init = new __WEBPACK_IMPORTED_MODULE_2__models_PokobanTransition__["a" /* PokobanTransition */]();
        init.state = state;
        init.action = 'INIT';
        init.success = true;
        this.pokoban.transitions.unshift(init);
        if (this.play) {
            // recursively perform transition
            this.transition(state, this.nextIndex);
        }
    };
    GameComponent.prototype.playOnOff = function () {
        // this.play has been updated here
        // restart playing, or clear the timer
        if (this.play) {
            this.transition(this.pokoban.transitions[this.nextIndex - 1].state, this.nextIndex);
        }
        else if (this.timer) {
            clearTimeout(this.timer);
        }
    };
    GameComponent.prototype.selectState = function (index) {
        //Stop timer or else we can get some weird state inteference
        if (this.timer)
            clearTimeout(this.timer);
        // Get current state which is the previous index
        var curState = this.pokoban.transitions[this.nextIndex - 1].state;
        this.nextIndex = index + 1;
        // Restart playing, or draw the new state which is a transitions
        // from the current state (no matter how different they are) and the new state
        if (this.play) {
            this.transition(curState, this.nextIndex);
        }
        else {
            this.drawTransition(curState, this.pokoban.transitions[this.nextIndex - 1].state);
        }
    };
    /**
     * Draws game environment
     *
     * @param {HTMLCanvasElement} canvas
     * @param state
     */
    GameComponent.prototype.drawState = function (canvas, state) {
        var _this = this;
        // clear the canvas
        this.ctx.clearRect(0, 0, canvas.width, canvas.height);
        this.ctx.font = '18px monospace';
        this.ctx.textAlign = "center";
        this.ctx.textBaseline = "middle";
        this.ctx.fillStyle = 'rgba(0, 0, 0, 0.5)';
        // draw getPage cells
        for (var x = 0; x < canvas.width; x += this.baseWidth) {
            for (var y = 0; y < canvas.height; y += this.baseHeight) {
                this.ctx.strokeRect(x, y, this.baseWidth, this.baseHeight);
            }
        }
        // draw the walls
        state.walls.forEach(function (wall) {
            _this.ctx.fillStyle = 'rgba(0, 0, 0, 1)';
            _this.ctx.fillRect(_this.baseWidth * wall.col, _this.baseHeight * wall.row, _this.baseWidth, _this.baseHeight);
        });
        // draw the goals
        state.goals.forEach(function (goal) { return _this.drawGoal(goal); });
        // draw the boxes
        state.boxes.forEach(function (box) { return _this.drawBox(box, _this.isSolved(box, state)); });
        // draw the agents
        state.agents.forEach(function (agent) { return _this.drawAgent(agent); });
    };
    /**
     * Animates a single transition
     *
     * @param {PokobanState} currentState
     * @param currentIndex
     * @returns {PokobanState}
     */
    GameComponent.prototype.transition = function (currentState, currentIndex) {
        var _this = this;
        if (this.pokoban.transitions.length <= currentIndex)
            return currentState;
        this.timer = setTimeout(function () {
            return _this.transition(_this.drawTransition(currentState, _this.pokoban.transitions[currentIndex].state), _this.nextIndex = currentIndex + 1);
        }, this.msPerAction);
    };
    /**
     * Transitions canvas into new state
     *
     * @param currentState
     * @param {PokobanState} nextState
     */
    GameComponent.prototype.drawTransition = function (currentState, nextState) {
        var _this = this;
        // remove agents & boxes from old position
        currentState.agents.concat(currentState.boxes).forEach(function (agent) {
            _this.ctx.clearRect(_this.baseWidth * agent.col, _this.baseHeight * agent.row, _this.baseWidth, _this.baseHeight);
            _this.ctx.strokeRect(_this.baseWidth * agent.col, _this.baseHeight * agent.row, _this.baseWidth, _this.baseHeight);
        });
        // insert gaols (might have been removed)
        nextState.goals.forEach(function (goal) {
            _this.drawGoal(goal);
        });
        // insert agents at new position
        nextState.agents.forEach(function (agent) {
            _this.drawAgent(agent);
        });
        // insert boxes at new position
        nextState.boxes.forEach(function (box) {
            _this.drawBox(box, _this.isSolved(box, nextState));
        });
        return nextState;
    };
    /**
     * Draws a goal
     *
     * @param {PokobanObject} goal
     */
    GameComponent.prototype.drawGoal = function (goal) {
        this.ctx.fillStyle = 'rgba(0, 255, 255, 1)';
        this.ctx.fillRect(this.baseWidth * goal.col, this.baseHeight * goal.row, this.baseWidth, this.baseHeight);
        this.ctx.fillStyle = 'rgba(255, 255, 255, 1)';
        this.ctx.fillText(goal.letter, this.baseWidth * goal.col + this.baseWidth / 2, this.baseHeight * goal.row + this.baseHeight / 2);
    };
    /**
     * Draws a box
     *
     * @param {PokobanObject} box
     * @param isSolved
     */
    GameComponent.prototype.drawBox = function (box, isSolved) {
        this.ctx.fillStyle = isSolved ? 'rgba(0, 255, 0, 0.8)' : 'rgba(0, 0, 255, 1)';
        this.ctx.fillRect(this.baseWidth * box.col, this.baseHeight * box.row, this.baseWidth, this.baseHeight);
        this.ctx.fillStyle = 'rgba(255, 255, 255, 1)';
        this.ctx.fillText(box.letter, this.baseWidth * box.col + this.baseWidth / 2, this.baseHeight * box.row + this.baseHeight / 2);
    };
    /**
     * Draws an agent
     *
     * @param agent
     */
    GameComponent.prototype.drawAgent = function (agent) {
        // circle
        this.ctx.fillStyle = 'rgba(0, 0, 255, 1)';
        this.ctx.beginPath();
        this.ctx.arc(this.baseWidth * agent.col + this.baseWidth / 2, this.baseWidth * agent.row + this.baseHeight / 2, this.baseHeight / 3, 0, Math.PI * 2, false);
        this.ctx.fill();
        // text
        this.ctx.fillStyle = 'rgba(255, 255, 255, 1)';
        this.ctx.fillText(agent.letter, this.baseWidth * agent.col + this.baseWidth / 2, this.baseHeight * agent.row + this.baseHeight / 2);
    };
    /**
     * Returns true if given box solves a goal
     *
     * @param {PokobanObject} box
     * @param state
     */
    GameComponent.prototype.isSolved = function (box, state) {
        return state.goals.some(function (goal) {
            return goal.row == box.row &&
                goal.col == box.col &&
                goal.letter === box.letter.toLowerCase();
        });
    };
    return GameComponent;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["A" /* HostListener */])('document:keypress', ['$event']),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", void 0)
], GameComponent.prototype, "handleKeyboardEvent", null);
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_16" /* ViewChild */])('canvas'),
    __metadata("design:type", Object)
], GameComponent.prototype, "canvas", void 0);
GameComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["o" /* Component */])({
        selector: 'game',
        template: __webpack_require__("../../../../../src/app/components/game/game.component.html"),
        styles: [__webpack_require__("../../../../../src/app/components/game/game.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */]) === "function" && _a || Object])
], GameComponent);

var _a;
//# sourceMappingURL=game.component.js.map

/***/ }),

/***/ "../../../../../src/app/components/games/games.component.html":
/***/ (function(module, exports) {

module.exports = "<table class=\"table\">\n  <thead>\n  <tr>\n    <th>GameID</th>\n    <th>Description</th>\n    <th>Level</th>\n    <th>Date</th>\n  </tr>\n  </thead>\n  <tr *ngFor=\"let pokoban of pokobans\">\n    <td><a [routerLink]=\"[pokoban._id]\" [queryParams]=\"{folder:'saves'}\">{{pokoban._id}}</a></td>\n    <td>{{pokoban.description}}</td>\n    <td>{{pokoban.level}}</td>\n    <td>{{pokoban.date | date: 'medium'}}</td>\n  </tr>\n</table>\n"

/***/ }),

/***/ "../../../../../src/app/components/games/games.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GamesComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var GamesComponent = (function () {
    function GamesComponent(route) {
        this.route = route;
    }
    GamesComponent.prototype.ngOnInit = function () {
        var response = this.route.snapshot.data['pokobans'];
        this.pokobans = response.data;
        this.total = response.total;
    };
    return GamesComponent;
}());
GamesComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["o" /* Component */])({
        selector: 'games',
        template: __webpack_require__("../../../../../src/app/components/games/games.component.html"),
        styleUrls: []
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */]) === "function" && _a || Object])
], GamesComponent);

var _a;
//# sourceMappingURL=games.component.js.map

/***/ }),

/***/ "../../../../../src/app/components/home/home.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/components/home/home.component.html":
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "../../../../../src/app/components/home/home.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HomeComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var HomeComponent = (function () {
    function HomeComponent() {
    }
    HomeComponent.prototype.ngOnInit = function () {
    };
    return HomeComponent;
}());
HomeComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["o" /* Component */])({
        selector: 'home',
        template: __webpack_require__("../../../../../src/app/components/home/home.component.html"),
        styles: [__webpack_require__("../../../../../src/app/components/home/home.component.css")]
    }),
    __metadata("design:paramtypes", [])
], HomeComponent);

//# sourceMappingURL=home.component.js.map

/***/ }),

/***/ "../../../../../src/app/components/levels/levels.component.html":
/***/ (function(module, exports) {

module.exports = "<pagination-controls style=\"display: flex;justify-content: center;\" (pageChange)=\"pageChange($event)\"></pagination-controls>\n<select [(ngModel)]=\"folder\">\n  <option *ngFor=\"let folder of folders\" [ngValue]=\"folder\">{{folder}}</option>\n</select>\n<table class=\"table\">\n  <thead>\n  <tr>\n    <th>Filename</th>\n    <!--<th>Height</th>-->\n    <!--<th>Width</th>-->\n  </tr>\n  </thead>\n  <tr *ngFor=\"let level of levels | paginate: { itemsPerPage: pageSize, currentPage: curPage, totalItems: total}\">\n    <td><a [routerLink]=\"['/games/state/' + level]\">{{level}}</a></td>\n    <!--<td>{{level.height}}</td>-->\n    <!--<td>{{level.width}}</td>-->\n  </tr>\n</table>\n"

/***/ }),

/***/ "../../../../../src/app/components/levels/levels.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LevelsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_LevelService__ = __webpack_require__("../../../../../src/app/services/LevelService.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LevelsComponent = (function () {
    function LevelsComponent(route, levelService) {
        this.route = route;
        this.levelService = levelService;
        this.pageSize = 20;
        this.curPage = 1;
        this.total = 0;
        this.folders = ['Supervised', 'Unsuperised'];
        this.folder = this.folders[1];
    }
    LevelsComponent.prototype.ngOnInit = function () {
        var resp = this.route.snapshot.data['paginationResp'];
        console.log(resp);
        this.levels = resp.data;
        this.total = resp.total;
    };
    LevelsComponent.prototype.pageChange = function ($event) {
        var _this = this;
        this.levelService.getPage($event, this.pageSize, this.folder)
            .then(function (resp) {
            console.log(resp);
            _this.levels = resp.data;
            _this.total = resp.total;
            _this.curPage = $event;
        });
    };
    return LevelsComponent;
}());
LevelsComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["o" /* Component */])({
        selector: 'levels',
        template: __webpack_require__("../../../../../src/app/components/levels/levels.component.html")
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__services_LevelService__["a" /* LevelService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__services_LevelService__["a" /* LevelService */]) === "function" && _b || Object])
], LevelsComponent);

var _a, _b;
//# sourceMappingURL=levels.component.js.map

/***/ }),

/***/ "../../../../../src/app/components/replays/replays.component.html":
/***/ (function(module, exports) {

module.exports = "<table class=\"table\">\n  <thead>\n  <tr>\n    <th>GameID</th>\n    <th>Description</th>\n    <th>Level</th>\n    <th>Date</th>\n  </tr>\n  </thead>\n  <tr *ngFor=\"let pokoban of pokobans\">\n    <td><a [routerLink]=\"['/games',pokoban.id]\" [queryParams]=\"{folder:'replays'}\">{{pokoban.id}}</a></td>\n    <td>{{pokoban.description}}</td>\n    <td>{{pokoban.level}}</td>\n    <td>{{pokoban.date | date: 'medium'}}</td>\n  </tr>\n</table>\n"

/***/ }),

/***/ "../../../../../src/app/components/replays/replays.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return ReplaysComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/@angular/router.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var ReplaysComponent = (function () {
    function ReplaysComponent(route) {
        this.route = route;
    }
    ReplaysComponent.prototype.ngOnInit = function () {
        var response = this.route.snapshot.data['pokobans'];
        this.pokobans = response.data;
        this.total = response.total;
    };
    return ReplaysComponent;
}());
ReplaysComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["o" /* Component */])({
        selector: 'replays',
        template: __webpack_require__("../../../../../src/app/components/replays/replays.component.html"),
        styleUrls: []
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */]) === "function" && _a || Object])
], ReplaysComponent);

var _a;
//# sourceMappingURL=replays.component.js.map

/***/ }),

/***/ "../../../../../src/app/guards/LevelsGuard.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LevelsGuard; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_LevelService__ = __webpack_require__("../../../../../src/app/services/LevelService.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var LevelsGuard = (function () {
    function LevelsGuard(levelService) {
        this.levelService = levelService;
    }
    LevelsGuard.prototype.resolve = function (route, state) {
        return this.levelService.getPage(1, 20, "unsupervised");
    };
    return LevelsGuard;
}());
LevelsGuard = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__services_LevelService__["a" /* LevelService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__services_LevelService__["a" /* LevelService */]) === "function" && _a || Object])
], LevelsGuard);

var _a;
//# sourceMappingURL=LevelsGuard.js.map

/***/ }),

/***/ "../../../../../src/app/guards/PokobanGamesGuard.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PokobanGamesGuard; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__ = __webpack_require__("../../../../../src/app/services/PokobanService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var PokobanGamesGuard = (function () {
    function PokobanGamesGuard(pokobanService) {
        this.pokobanService = pokobanService;
    }
    PokobanGamesGuard.prototype.resolve = function (route, state) {
        return this.pokobanService.experts(1, 20);
    };
    return PokobanGamesGuard;
}());
PokobanGamesGuard = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__["a" /* PokobanService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__["a" /* PokobanService */]) === "function" && _a || Object])
], PokobanGamesGuard);

var _a;
//# sourceMappingURL=PokobanGamesGuard.js.map

/***/ }),

/***/ "../../../../../src/app/guards/PokobanGuard.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PokobanGuard; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__ = __webpack_require__("../../../../../src/app/services/PokobanService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var PokobanGuard = (function () {
    function PokobanGuard(http, pokobanService) {
        this.http = http;
        this.pokobanService = pokobanService;
    }
    PokobanGuard.prototype.resolve = function (route, state) {
        return this.pokobanService.one(route.params['id'], route.queryParams['folder']);
    };
    return PokobanGuard;
}());
PokobanGuard = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_http__["a" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_http__["a" /* Http */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__["a" /* PokobanService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__["a" /* PokobanService */]) === "function" && _b || Object])
], PokobanGuard);

var _a, _b;
//# sourceMappingURL=PokobanGuard.js.map

/***/ }),

/***/ "../../../../../src/app/guards/PokobanReplaysGuard.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PokobanReplaysGuard; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__ = __webpack_require__("../../../../../src/app/services/PokobanService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var PokobanReplaysGuard = (function () {
    function PokobanReplaysGuard(pokobanService) {
        this.pokobanService = pokobanService;
    }
    PokobanReplaysGuard.prototype.resolve = function (route, state) {
        return this.pokobanService.replays(1, 20);
    };
    return PokobanReplaysGuard;
}());
PokobanReplaysGuard = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__["a" /* PokobanService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__services_PokobanService__["a" /* PokobanService */]) === "function" && _a || Object])
], PokobanReplaysGuard);

var _a;
//# sourceMappingURL=PokobanReplaysGuard.js.map

/***/ }),

/***/ "../../../../../src/app/guards/PokobanStateGuard.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PokobanStateGuard; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__services_LevelService__ = __webpack_require__("../../../../../src/app/services/LevelService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var PokobanStateGuard = (function () {
    function PokobanStateGuard(http, levelService) {
        this.http = http;
        this.levelService = levelService;
    }
    PokobanStateGuard.prototype.resolve = function (route, state) {
        return this.levelService.state(route.params['file']);
    };
    return PokobanStateGuard;
}());
PokobanStateGuard = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_2__angular_http__["a" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_http__["a" /* Http */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_0__services_LevelService__["a" /* LevelService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__services_LevelService__["a" /* LevelService */]) === "function" && _b || Object])
], PokobanStateGuard);

var _a, _b;
//# sourceMappingURL=PokobanStateGuard.js.map

/***/ }),

/***/ "../../../../../src/app/models/PokobanTransition.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PokobanTransition; });
var PokobanTransition = (function () {
    function PokobanTransition() {
    }
    return PokobanTransition;
}());

//# sourceMappingURL=PokobanTransition.js.map

/***/ }),

/***/ "../../../../../src/app/services/DataService.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DataService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var DataService = (function () {
    function DataService(http) {
        this.http = http;
        this.endPoint = 'http://localhost:5000/api';
    }
    /**
     *
     * @param {string} url
     * @returns {Promise}
     */
    DataService.prototype.get = function (url) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.http.get(_this.endPoint + "/" + url).subscribe(function (response) {
                resolve(response.json());
            });
        });
    };
    /**
     * T is the expected object to be wrapped in a PaginationResponse.
     * @param {string} url: Endpoint
     * @param {number} page: the page to view, starting at 1
     * @param {number} pageSize: The number of items per page
     * @returns {Promise<PaginationResponse<T>>}
     */
    DataService.prototype.paginate = function (url, page, pageSize) {
        var _this = this;
        var params = new __WEBPACK_IMPORTED_MODULE_0__angular_http__["c" /* URLSearchParams */]();
        params.set('skip', ((page - 1) * pageSize).toString());
        params.set('limit', pageSize.toString());
        return new Promise(function (resolve, reject) {
            _this.http.get(_this.endPoint + "/" + url + "?" + params.toString()).subscribe(function (response) {
                resolve(response.json());
            });
        });
    };
    return DataService;
}());
DataService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_0__angular_http__["a" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_0__angular_http__["a" /* Http */]) === "function" && _a || Object])
], DataService);

var _a;
//# sourceMappingURL=DataService.js.map

/***/ }),

/***/ "../../../../../src/app/services/LevelService.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LevelService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__DataService__ = __webpack_require__("../../../../../src/app/services/DataService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var LevelService = (function (_super) {
    __extends(LevelService, _super);
    function LevelService(http) {
        return _super.call(this, http) || this;
    }
    LevelService.prototype.getPage = function (page, pageSize, folder) {
        return _super.prototype.paginate.call(this, "levels/" + folder + "?", page, pageSize);
    };
    LevelService.prototype.one = function (filename) {
        return _super.prototype.get.call(this, "levels/" + filename);
    };
    LevelService.prototype.state = function (filename) {
        return _super.prototype.get.call(this, "levels/unsupervised/" + filename + "/state");
    };
    return LevelService;
}(__WEBPACK_IMPORTED_MODULE_0__DataService__["a" /* DataService */]));
LevelService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_2__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */]) === "function" && _a || Object])
], LevelService);

var _a;
//# sourceMappingURL=LevelService.js.map

/***/ }),

/***/ "../../../../../src/app/services/PokobanService.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PokobanService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__DataService__ = __webpack_require__("../../../../../src/app/services/DataService.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_http__ = __webpack_require__("../../../http/@angular/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var PokobanService = (function (_super) {
    __extends(PokobanService, _super);
    function PokobanService(http) {
        var _this = _super.call(this, http) || this;
        _this.baseUrl = 'pokoban';
        return _this;
    }
    PokobanService.prototype.experts = function (page, pageSize) {
        return _super.prototype.paginate.call(this, this.baseUrl + "/saves", page, pageSize);
    };
    PokobanService.prototype.replays = function (page, pageSize) {
        return _super.prototype.paginate.call(this, this.baseUrl + "/replays", page, pageSize);
    };
    PokobanService.prototype.oneMeta = function (id, folder) {
        return _super.prototype.get.call(this, this.baseUrl + "/" + folder + "/" + id);
    };
    return PokobanService;
}(__WEBPACK_IMPORTED_MODULE_0__DataService__["a" /* DataService */]));
PokobanService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_2__angular_core__["C" /* Injectable */])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_http__["a" /* Http */]) === "function" && _a || Object])
], PokobanService);

var _a;
//# sourceMappingURL=PokobanService.js.map

/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/@angular/platform-browser-dynamic.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_23" /* enableProdMode */])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
    .catch(function (err) { return console.log(err); });
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map