import { Router } from 'express';
import Container from '@/container';
import { WRONG_KEY } from '@/util/error';

const router = Router();

export default (indexRouter: Router): void => {
  indexRouter.use('/users', router);

  router.post('/register', async (req, res) => {
    const serviceInstance = Container.get('UserService');
    if (!serviceInstance) throw new Error(WRONG_KEY);
    const { id, name, password } = req.body;
    const registResult = await serviceInstance.register({ id, name, password });
    if (registResult) return res.status(200).json({ token: 'token' });
    return res.status(400).json({ message: 'This ID already exists.' });
  });

  router.post('/login', async (req, res) => {
    const serviceInstance = Container.get('UserService');
    if (!serviceInstance) throw new Error(WRONG_KEY);
    const { id, password } = req.body;
    const successLogin = await serviceInstance.login({ id, password });
    if (successLogin) return res.status(200).json({ token: 'token' });
    return res.status(404).json();
  });

  router.get('/exist/:id', async (req, res) => {
    const serviceInstance = Container.get('UserService');
    if (!serviceInstance) throw new Error(WRONG_KEY);
    const { id } = req.params;
    const isExistUser = await serviceInstance.validateUser(id);
    if (isExistUser) return res.status(200).json();
    return res.status(404).json();
  });
};
