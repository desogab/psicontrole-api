import { Router } from "express";

import { isAuthenticated } from "./middlewares/isAuthenticated";
import { AuthProfessionalUserController } from "./models/account/useCases/authProfessionalUser/AuthProfessionalUserController";
import { CreateClientController } from "./models/client/useCases/createClient/CreateClientController";
import { CreateProfessionalUseController } from "./models/professional/useCases/createProfessionalUser/CreateProfessionalUserController";
import { UpdateProfessionalInfoController } from "./models/professional/useCases/updateProfessionalInfo/UpdateProfessionalInfoController";

const routes = Router();

const createProfessionalUser = new CreateProfessionalUseController();
const authProfessionalUser = new AuthProfessionalUserController();
const updateProfessionalInfo = new UpdateProfessionalInfoController();

const createClient = new CreateClientController();

routes.post("/professional/create", createProfessionalUser.handle);
routes.post("/professional/auth", authProfessionalUser.handle);
routes.put(
  "/professional/update-info",
  isAuthenticated,
  updateProfessionalInfo.handle
);

routes.post("/client/create", isAuthenticated, createClient.handle);

export { routes };
