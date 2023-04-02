<template>
	<el-row  type="flex" justify="center" class="register-container">
		<el-col :span="6">
			<el-card class="register-card" shadow="always">
				<div slot="header" class="register-brand" style="font-style: italic;font-size: 30px; margin: 5px;">欢迎注册</div>
				<el-form :model="registerForm" status-icon :rules="rules" ref="registerForm" label-width="80px" class="web-ruleForm">
					
					<el-form-item label="用户名" prop="userName" class="form-item">
						<el-input type="userName" v-model="registerForm.userName" autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item label="昵称" prop="nickName" class="form-item">
						<el-input type="nickName" v-model="registerForm.nickName" autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="password" class="form-item">
						<el-input type="password" v-model="registerForm.password" autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="confirmPassword" class="form-item">
						<el-input type="password" v-model="registerForm.confirmPassword" autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item class="form-button">
						<el-button type="primary" @click="submitForm('registerForm')">注册</el-button>
						<el-button @click="resetForm('registerForm')">清空</el-button>
					</el-form-item>
					<div class="to-login">
						<router-link to="/login">已有账号,前往登录</router-link>
					</div>
				</el-form>
			</el-card>
		</el-col>
	</el-row>
</template>

<script>
	export default {
		name: "login",
		data() {
			var checkUserName = (rule, value, callback) => {
				if (!value) {
					return callback(new Error('请输入用户名'));
				}
				callback();
			};
			var checkNickName = (rule, value, callback) => {
				if (!value) {
					return callback(new Error('请输入昵称'));
				}
				callback();
			};
			var checkPassword = (rule, value, callback) => {
				if (value === '') {
					return callback(new Error('请输入密码'));
				}
				callback();
			};

			var checkConfirmPassword = (rule, value, callback) => {
				console.log("checkConfirmPassword");
				if (value === '') {
					return callback(new Error('请输入密码'));
				}
				if (value != this.registerForm.password) {
					return callback(new Error('两次密码输入不一致'));
				}
				callback();
			};


			return {
				registerForm: {
					userName: '',
					nickName: '',
					password: '',
					confirmPassword: ''
				},
				rules: {
					userName: [{
						validator: checkUserName,
						trigger: 'blur'
					}],
					nickName: [{
						validator: checkNickName,
						trigger: 'blur'
					}],
					password: [{
						validator: checkPassword,
						trigger: 'blur'
					}],
					confirmPassword: [{
						validator: checkConfirmPassword,
						trigger: 'blur'
					}]
				}
			};
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http({
								url: "/register",
								method: 'post',
								data: this.registerForm
							})
							.then((data) => {
								this.$message.success("注册成功!");
							})
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
</script>

<style scoped lang="scss">
.register-container{
	background:  linear-gradient(#c1fcf5, #124d4d); 
	background-size: cover;
	height: 100%;
	.register-card {
		margin-top: 6vw;
		border-radius: 0px 70px;
		.web-ruleForm {
			height: 435px;
			padding: 5px;
			overflow: hidden;
			.form-item{
				margin-right: 40px;
				margin-top: 30px;
			}
			.form-button{
				margin-left: 180px;
				margin-top: 50px;
			}
			.to-login{
				margin-left: 270px;
				margin-top: 40px;
			}
		}
	}

}

	
</style>
